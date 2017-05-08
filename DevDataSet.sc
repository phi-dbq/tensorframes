// -*- mode: scala -*-
/**
  *  Generic I/O utilities for constructing paths
  */

import java.nio.file.{Paths, Path, Files}
import org.apache.spark.sql.types._
import org.apache.spark.sql._

object IOUtils {

  object Implicits {
    implicit def string2fpath(s: String): FPath = new FPath(Paths.get(s))
    implicit def path2fpath(p: Path): FPath = new FPath(p)
    implicit def fpath2path(fp: FPath): Path = fp.fp
  }

  case class FPath(fp: Path) {
    import Implicits._
    private[this] lazy val fh = fp.toFile
    def /(sub: FPath) = new FPath(fp.resolve(sub.fp))
    def exists: Boolean = fh.exists
    override def toString: String = fp.toString
  }

  object FPath {
    val home = new FPath(Paths.get(System.getProperty("user.home")))
  }
}

import IOUtils._, IOUtils.Implicits._

object ImageDataSrc {

  val fpLocalData = FPath.home / "local" / "data" / "images"
  
  def load(name: String)(implicit spark: SparkSession): DataFrame = {
    val fpImages = fpLocalData / s"${name}.parquet"
    if (! fpImages.exists) {
      val fpJson = fpLocalData / s"${name}.json"
      require(fpJson.exists)

      val imgSchema = StructType(Seq(
        StructField("data", BinaryType, false),
        StructField("height", IntegerType, false),
        StructField("mode", StringType, false),
        StructField("nChannels", IntegerType, false),
        StructField("width", IntegerType, false)    
      ))
      val schema = StructType(Seq(
        StructField("image", imgSchema, false),
        StructField("labels", ArrayType(StringType), false),
        StructField("uri", StringType, true)
      ))
      val df = spark.read.schema(schema).json(fpJson.toString)
      df.write.mode("overwrite").parquet(fpImages.toString)
    }
    spark.read.parquet(fpImages.toString).cache()
  }
}
