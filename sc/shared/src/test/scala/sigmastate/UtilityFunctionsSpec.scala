package sigmastate

import sigmastate.helpers.CompilerTestingCommons
import sigmastate.helpers.TestingHelpers._
import sigma.ast.SBoolean
import sigmastate.interpreter.Interpreter._

/**
 * Tests for utility functions added in Issue #1037
 * These functions are compiler-level helpers that desugar into standard ErgoTree primitives.
 */
class UtilityFunctionsSpec extends CompilerTestingCommons {
  implicit lazy val IR: TestingIRContext = new TestingIRContext

  property("verifyBoxHasMarkerToken - should compile and desugar correctly") {
    val script =
      s"""{
         |  val box = OUTPUTS(0)
         |  val tokenId = fromBase16("${"01" * 32}")
         |  verifyBoxHasMarkerToken(box, tokenId)
         |}""".stripMargin

    val result = compile(emptyEnv, script)
    result.tpe shouldBe SBoolean
  }

  property("verifyBoxHasNoMarkerToken - should compile and desugar correctly") {
    val script =
      s"""{
         |  val box = OUTPUTS(0)
         |  val tokenId = fromBase16("${"01" * 32}")
         |  verifyBoxHasNoMarkerToken(box, tokenId)
         |}""".stripMargin

    val result = compile(emptyEnv, script)
    result.tpe shouldBe SBoolean
  }

  property("verifyUsedAdditionalRegisters - should compile and desugar correctly") {
    val script =
      """{
        |  val box = OUTPUTS(0)
        |  verifyUsedAdditionalRegisters(box, 2)
        |}""".stripMargin

    val result = compile(emptyEnv, script)
    result.tpe shouldBe SBoolean
  }

  property("verifySameForBasicRequiredRegisters - should compile and desugar correctly") {
    val script =
      """{
        |  val inBox = INPUTS(0)
        |  val outBox = OUTPUTS(0)
        |  verifySameForBasicRequiredRegisters(inBox, outBox)
        |}""".stripMargin

    val result = compile(emptyEnv, script)
    result.tpe shouldBe SBoolean
  }

  property("verifySameForRequiredRegisters - should compile and desugar correctly") {
    val script =
      """{
        |  val inBox = INPUTS(0)
        |  val outBox = OUTPUTS(0)
        |  verifySameForRequiredRegisters(inBox, outBox)
        |}""".stripMargin

    val result = compile(emptyEnv, script)
    result.tpe shouldBe SBoolean
  }

  property("verifySpentToken - should compile and desugar correctly") {
    val script =
      s"""{
         |  val inBox = INPUTS(0)
         |  val outBox = OUTPUTS(0)
         |  val tokenId = fromBase16("${"01" * 32}")
         |  verifySpentToken(inBox, outBox, tokenId, 1L)
         |}""".stripMargin

    val result = compile(emptyEnv, script)
    result.tpe shouldBe SBoolean
  }

  property("all utility functions in combined usage") {
    val script =
      s"""{
         |  val inputBox = INPUTS(0)
         |  val outputBox = OUTPUTS(0)
         |  val nftId = fromBase16("${"AA" * 32}")
         |  
         |  val hasMarker = verifyBoxHasMarkerToken(inputBox, nftId)
         |  val sameBasics = verifySameForBasicRequiredRegisters(inputBox, outputBox)
         |  val validRegs = verifyUsedAdditionalRegisters(outputBox, 2)
         |  
         |  hasMarker && sameBasics && validRegs
         |}""".stripMargin

    val result = compile(emptyEnv, script)
    result.tpe shouldBe SBoolean
  }
}
