package sigma.serialization

import sigma.VersionContext
import sigma.ast.{ConcreteCollection, EQ, IntArrayConstant, IntConstant, SInt, SubstConstants, Upcast}
import sigma.ast.syntax.IntValue
import sigma.serialization.ErgoTreeSerializer.DefaultSerializer
import sigmastate.CrossVersionProps

class SubstConstantsSerializerSpecification extends SerializationSpecification
  with CrossVersionProps {

  property("SubstConstant deserialization round trip") {
    forAll(numExprTreeNodeGen) { propRaw =>
      val prop = if(VersionContext.current.isV3OrLaterErgoTreeVersion && propRaw.tpe.isInstanceOf[SInt.type]) {
        Upcast(propRaw, SInt)
      } else {
        propRaw
      }
      val tree = mkTestErgoTree(EQ(prop, IntConstant(1)).toSigmaProp)
      val bytes = DefaultSerializer.serializeErgoTree(tree)
      val newVals = ConcreteCollection(Array[IntValue](1), SInt)
      val expr = SubstConstants(bytes, IntArrayConstant(Array(0)), newVals)
      roundTripTest(expr)
    }
  }

}