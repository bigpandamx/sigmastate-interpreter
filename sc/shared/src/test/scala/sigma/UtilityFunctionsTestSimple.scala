import org.scalatest.matchers.should.Matchers
import org.scalatest.propspec.AnyPropSpec
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks
import sigma.{Box, Coll}

/**
 * Simple test to verify that our utility functions exist in the API.
 * This is a basic compilation test to ensure the API is available and accessible.
 */
class UtilityFunctionsTestSimple extends AnyPropSpec with ScalaCheckPropertyChecks with Matchers {

  property("All utility functions should be accessible via SigmaDsl") {
    // Simple compilation test - if these methods don't exist, compilation will fail
    val dsl = sigma.eval.SigmaDsl
    
    // This tests that the methods exist and have correct signatures
    // We use type ascription to verify the return types
    val hasMarkerTokenMethod: (Box, Coll[Byte]) => Boolean = dsl.verifyBoxHasMarkerToken
    val noMarkerTokenMethod: (Box, Coll[Byte]) => Boolean = dsl.verifyBoxHasNoMarkerToken
    val usedRegistersMethod: (Box, Int) => Boolean = dsl.verifyUsedAdditionalRegisters
    val basicRequiredMethod: (Box, Box) => Boolean = dsl.verifySameForBasicRequiredRegisters
    val requiredMethod: (Box, Box) => Boolean = dsl.verifySameForRequiredRegisters
    val spentTokenMethod: (Box, Box, Coll[Byte], Long) => Boolean = dsl.verifySpentToken
    
    // If we reach here, all methods exist with correct signatures
    succeed
  }

  property("Utility function names should be correct") {
    // Type-based test - if methods don't exist, compilation fails
    // This is Scala.js compatible (no reflection needed)
    val dsl = sigma.eval.SigmaDsl
    
    // Test each method can be referenced as a function value (JS-compatible syntax)
    locally {
      val _: (Box, Coll[Byte]) => Boolean = dsl.verifyBoxHasMarkerToken(_, _)
    }
    locally {
      val _: (Box, Coll[Byte]) => Boolean = dsl.verifyBoxHasNoMarkerToken(_, _)
    }  
    locally {
      val _: (Box, Int) => Boolean = dsl.verifyUsedAdditionalRegisters(_, _)
    }
    locally {
      val _: (Box, Box) => Boolean = dsl.verifySameForBasicRequiredRegisters(_, _)
    }
    locally {
      val _: (Box, Box) => Boolean = dsl.verifySameForRequiredRegisters(_, _)
    }
    locally {
      val _: (Box, Box, Coll[Byte], Long) => Boolean = dsl.verifySpentToken(_, _, _, _)
    }
    
    // If compilation succeeds, all methods exist with correct names and signatures
    succeed
  }
}