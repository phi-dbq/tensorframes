// Default versions and dependcies
val sparkVer = sys.props.getOrElse("spark.version", "2.1.1")
val sparkBranch = sparkVer.substring(0, 3)
val defaultScalaVer = sparkBranch match {
  case "2.1" => "2.11.8"
  case "2.2" => "2.11.8"
  case _ => throw new IllegalArgumentException(s"Unsupported Spark version: $sparkVer.")
}
val scalaVer = sys.props.getOrElse("scala.version", defaultScalaVer)
val defaultScalaTestVer = scalaVer match {
  case s if s.startsWith("2.10") => "2.0"
  case s if s.startsWith("2.11") => "2.2.6" // scalatest_2.11 does not have 2.0 published
}
val scalaMajorVersion = scalaVer.split(".").take(2).mkString(".")

val protobufVer = "3.3.1"  // use protocol buffer, will be shaded
val tensorflowVer = "1.2.1"
val ammoniteVer = "0.9.9"

lazy val root = (project in file("."))
  .settings(commonSettings: _*)
  .settings(sparkPackageSettings: _*)
  .settings(tensorflowSettings: _*)
  .settings(replSettings: _*)  

lazy val sparkPackageSettings = Seq(
  sparkVersion := sparkVer,
  spName := "databricks/tensorframes",
  version := s"0.2.8-phi-SNAPSHOT-spark$sparkBranch",
  sparkComponents ++= Seq(
    "core", "sql"
  ),
  spAppendScalaVersion := true
)

lazy val sparkDependencies = Seq(
  "core", "sql", "mllib"
).map { mod =>
  "org.apache.spark" %% s"spark-$mod" % sparkVer
}

lazy val replSettings = Seq(
  libraryDependencies ++= sparkDependencies ++ Seq(
    "com.lihaoyi" % s"ammonite_${scalaVersion.value}" % ammoniteVer,
    "org.scalameta" %% "scalameta" % "1.7.0"
  )
)

// Additional tasks to generate keys
lazy val genClasspath = taskKey[Unit]("Build runnable script with classpath")
lazy val devToolSettings = Seq(
  genClasspath := {
    import java.io.PrintWriter
    
    val sbtPathRoot = baseDirectory.value / ".sbt.paths"
    sbtPathRoot.mkdirs()

    def writeClasspath(cpType: String)(R: => String): Unit = {
      val fout = new PrintWriter((sbtPathRoot / s"SBT_${cpType}_CLASSPATH").toString)
      println(s"Building ${cpType} classpath for current project")
      try fout.write(R) finally fout.close()
    }

    writeClasspath("RUNTIME") {
      (fullClasspath in Runtime).value.files.map(_.toString).mkString(":")
    }

    writeClasspath("SPARK_PACKAGE") {
      import scala.util.matching.Regex
      val patt = s"(.+?)/(.+?):(.+?)(-s_${scalaMajorVersion})?".r
      val pkgs = spDependencies.value.map { _ match {
        case patt(orgName, pkgName, pkgVer, stem, _*) =>
          if (null != stem) {
            println(s"org ${orgName}, pkg ${pkgName}, ver ${pkgVer}, ${stem}")
            s"${pkgName}-${pkgVer}${stem}.jar"
          } else {
            println(s"org ${orgName}, pkg ${pkgName}, ver ${pkgVer}")
            s"${pkgName}-${pkgVer}.jar"
          }
      }}.toSet
        (fullClasspath in Compile).value.files
        .filter(pkgs contains _.getName())
        .map(_.toString)
        .mkString(":")
    }
  })

lazy val tensorflowSettings = Seq(
  libraryDependencies ++= Seq(
    "com.google.protobuf" % "protobuf-java" % protobufVer,
    "org.tensorflow" % "proto" % tensorflowVer,
    "org.tensorflow" % "libtensorflow_jni" % tensorflowVer,
    "org.tensorflow" % "libtensorflow" % tensorflowVer,
    "org.tensorflow" % "tensorflow" % tensorflowVer
  ),

  // Shading rules
  assemblyShadeRules in assembly := Seq(
    ShadeRule.rename("com.google.protobuf.**" -> "shade.protobuf3.@1")
      .inAll
      //.inLibrary("com.google.protobuf" % "protobuf-java" % protobufVer)
  )
)

lazy val commonSettings = devToolSettings ++ Seq(
  version := "0.2.8-dl",
  scalaVersion := scalaVer,
  licenses := Seq("Apache-2.0" -> url("http://opensource.org/licenses/Apache-2.0")),
  organization := "databricks",
  // System conf
  parallelExecution := false,
  javaOptions in run += "-Xmx6G",
  // Add all the python files in the final binary
  unmanagedResourceDirectories in Compile ++= Seq(
    baseDirectory.value / "src/main/python/",
    baseDirectory.value / "src/main/proto/"
  ),
  libraryDependencies ++= Seq(
    "org.scalactic" %% "scalactic" % "3.0.0",
    "org.apache.commons" % "commons-lang3" % "3.4",
    "com.typesafe.scala-logging" %% "scala-logging-api" % "2.1.2",
    "com.typesafe.scala-logging" %% "scala-logging-slf4j" % "2.1.2"
  ),

  // Spark packages does not like this part
  test in assembly := {}
)

// lazy val shadedDependencies = Seq(
//   "com.google.protobuf" % "protobuf-java" % protobufVer
// )

// lazy val nonShadedDependencies = Seq(
//   // Normal dependencies
//   ModuleID("org.apache.commons", "commons-proxy", "1.0"),
//   "org.scalactic" %% "scalactic" % "3.0.0",
//   "org.apache.commons" % "commons-lang3" % "3.4",
//   "com.typesafe.scala-logging" %% "scala-logging-api" % "2.1.2",
//   "com.typesafe.scala-logging" %% "scala-logging-slf4j" % "2.1.2",
//   // TensorFlow dependencies
//   "org.tensorflow" % "tensorflow" % tensorflowVer
// )

// lazy val shaded = (project in file("."))
//   .settings(
//     name := "shaded",
//     libraryDependencies ++= nonShadedDependencies.map(_ % "provided"),
//     libraryDependencies ++= sparkDependencies.map(_ % "provided"),
//     libraryDependencies ++= shadedDependencies,
//     libraryDependencies ++= testDependencies,
//     //libraryDependencies ++= allPlatformDependencies,
//     target := target.value / "shaded", // have a separate target directory to make sbt happy
//     assemblyShadeRules in assembly := Seq(
//       ShadeRule.rename("com.google.protobuf.**" -> "org.tensorframes.protobuf3shade.@1").inAll
//     ),
//     assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = false)
//   ) // add all other settings

// // lazy val distribute = (project in file("."))
// //   .settings(
// //     name := "distribution",
// //     spName := "databricks/tensorframe",
// //     target := target.value / "distribution",
// //     // THIS IS THE MOST IMPORTANT SETTING
// //     spShade := true, 
// //     // this will pick up the shaded jar for distribution
// //     assembly in spPackage := (assembly in shaded).value, 
// //     // have all your non shaded dependencies here so that we can generate a clean pom
// //     libraryDependencies := nonShadedDependencies
// //   ) // add all other settings


// lazy val testDependencies = Seq(
//   // Test dependencies
//   "org.scalatest" %% "scalatest" % "3.0.0" % "test"
// )
