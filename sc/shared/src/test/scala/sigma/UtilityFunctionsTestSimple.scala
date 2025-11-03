import org.scalatest.matchers.should.Matchers
import org.scalatest.propspec.AnyPropSpec
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks
import sigma._

/**
 * Simple test to verify that our utility functions compile and exist.
 * This is a basic smoke test to ensure the API is available.
 */
class UtilityFunctionsTestSimple extends SigmaDslTesting { suite =>

  property("verifyBoxHasMarkerToken method should exist") {
    // Test that the method exists in the SigmaDslBuilder
    val method = sigma.eval.SigmaDsl.getClass.getMethods.find(_.getName == "verifyBoxHasMarkerToken")
    method shouldBe defined
  }

  property("verifyBoxHasNoMarkerToken method should exist") {
    // Test that the method exists in the SigmaDslBuilder  
    val method = sigma.eval.SigmaDsl.getClass.getMethods.find(_.getName == "verifyBoxHasNoMarkerToken")
    method shouldBe defined
  }

  property("verifyUsedAdditionalRegisters method should exist") {
    // Test that the method exists in the SigmaDslBuilder
    val method = sigma.eval.SigmaDsl.getClass.getMethods.find(_.getName == "verifyUsedAdditionalRegisters")
    method shouldBe defined
  }

  property("verifySameForBasicRequiredRegisters method should exist") {
    // Test that the method exists in the SigmaDslBuilder
    val method = sigma.eval.SigmaDsl.getClass.getMethods.find(_.getName == "verifySameForBasicRequiredRegisters")
    method shouldBe defined
  }

  property("verifySameForRequiredRegisters method should exist") {
    // Test that the method exists in the SigmaDslBuilder
    val method = sigma.eval.SigmaDsl.getClass.getMethods.find(_.getName == "verifySameForRequiredRegisters")
    method shouldBe defined
  }

  property("verifySpentToken method should exist") {
    // Test that the method exists in the SigmaDslBuilder
    val method = sigma.eval.SigmaDsl.getClass.getMethods.find(_.getName == "verifySpentToken")
    method shouldBe defined
  }
}