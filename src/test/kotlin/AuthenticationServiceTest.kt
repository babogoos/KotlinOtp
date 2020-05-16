import junit.framework.Assert.assertTrue
import org.junit.Test

class AuthenticationServiceTest {
    @Test
    fun is_valid() {
        val authenticationService = AuthenticationService()

        val isValid = authenticationService.isValid("joey", "91000000")

        assertTrue(isValid)
    }
}