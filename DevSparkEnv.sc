// -*- mode: scala -*-
import java.util.concurrent.atomic.AtomicInteger

import scala.collection.JavaConverters._
import org.apache.spark.SparkContext
import org.apache.spark.broadcast.Broadcast
import org.apache.spark.sql.expressions.UserDefinedFunction
import org.apache.spark.sql.catalyst.expressions.{Expression, ScalaUDF}
import org.apache.spark.sql.functions.struct
import org.apache.spark.sql.types._
import org.apache.spark.sql._

// https://github.com/coursier/coursier
// import $ivy.`databricks:tensorframes:0.2.8-s_2.11`
// import $ivy.`graphframes:graphframes:0.5.0-spark2.1-s_2.11`

implicit val spark = SparkSession.builder.master("local[4]").appName("spark-amm").getOrCreate()
implicit val sc = spark.sparkContext
// Run PageRank algorithm, and show results.
// A lot of WARN shows with GraphX and GraphFrame
// https://github.com/apache/spark/pull/16513
sc.setLogLevel("ERROR") // work-around: raising the report level
