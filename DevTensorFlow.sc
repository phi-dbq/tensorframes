// -*- scala -*-
// We don't seem to be able to get the names directly,
// but through python we can actually get all of them
// So initialize a python shell along the way to check the graph definition
// An `Output` is a symbolic representation of the Tensor
// There is really no `Input` as these are inferred from the knowledge of the graph
// REF: https://www.tensorflow.org/api_docs/java/reference/org/tensorflow/Operation

import $exec.DevDataSet, DevDataSet._

import scala.collection.JavaConverters._

import org.{tensorflow => tf}
import org.tensorflow.{framework => tfrm}
import org.tensorflow.framework.{
  AttrValue, DataType => ProtoDataType,
  GraphDef, NodeDef, TensorProto, TensorShapeProto
}
println(s"TensorFlow version: ${tf.TensorFlow.version}")

object TFOps {

  type GraphDef = Array[Byte]
  implicit def gdefbytes2gdef(bytes: GraphDef): tfrm.GraphDef = {
    tfrm.GraphDef.parseFrom(bytes)
  }

  case class GraphFunction(
    gdef: GraphDef,
    inputNames: Seq[String],
    outputNames: Seq[String]
  ) {
    @transient private lazy val graphDef = tfrm.GraphDef.parseFrom(gdef)
    @transient private lazy val nodeDict = graphDef.getNodeList.asScala.map {
      node => node.getName -> node
    }.toMap
    def node(name: String): tfrm.NodeDef = {
      nodeDict.getOrElse(name, throw new IllegalArgumentException("no op found"))
    }
    def getNodeDict = nodeDict
  }

  object GraphFunction {
    def apply(fp: Path, inputNames: Seq[String], outputNames: Seq[String]): GraphFunction = {
      new GraphFunction(Files.readAllBytes(fp), inputNames, outputNames)
    }
  }

  case class IsolatedSession(sess: tf.Session, g: tf.Graph) {
    def op(name: String): tf.Operation = {
      val op = g.operation(name)
      require(null != op, s"Graph operation [$name] not fouhnd")
      op
    }
    def op(elem: tf.Operation): tf.Operation = elem
    def op(tnsr: tf.Output): tf.Operation = tnsr.op

    def importGraphFunction(gfn: GraphFunction, name: Option[String] = None) = {
      val bytes = gfn.gdef.toByteArray
      val getNodeOp = name match {
        case Some(scope) => 
          g.importGraphDef(bytes, scope)
          val impl: String => tf.Operation = { vn => g.operation(s"$scope/$vn") }
          impl
        case None => 
          g.importGraphDef(bytes)
          g.operation _
      }
      val feeds = gfn.inputNames.map(getNodeOp)
      val fetches = gfn.outputNames.map(getNodeOp)
      feeds -> fetches
    }

    def showOp(name: String): Unit = {
      val op = g.operation(name)
      require(null != op, s"Graph operation [$name] not fouhnd")
      tfx.show(op)
    }
  }

  object tfx {
    def show(op: tf.Operation): Unit = {
      println(s"name: ${op.name}, #outputs: ${op.numOutputs}, type: ${op.`type`}")
        (0 until op.numOutputs).foreach { idx =>
          val tnsrOut = op.output(idx)
          println(tnsrOut.shape, tnsrOut.dataType)
        }
    }
  }

  object IsolatedSession {

    def apply(): IsolatedSession = apply(new tf.Graph())

    def apply(g: tf.Graph): IsolatedSession = {
      new IsolatedSession(new tf.Session(g), g)
    }

    def apply(gfn: GraphFunction): IsolatedSession = {
      val g = new tf.Graph()
      g.importGraphDef(gfn.gdef.toByteArray)
      apply(g)
    }
  }
}
