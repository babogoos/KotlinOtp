import io.mockk.every
import io.mockk.mockk
import junit.framework.Assert.assertTrue
import org.junit.Test

class AuthenticationServiceTest {

    private val profile = mockk<ProfileDao>(relaxed = true)
    private val tokenDao = mockk<RsaTokenDao>(relaxed = true)
    private val authenticationService = AuthenticationService(profile, tokenDao)

    @Test
    fun is_valid() {
        givenPassword("joey", "91")
        givenToken("000000")
        shouldBeValid("joey", "91000000")
    }

    private fun shouldBeValid(account: String, passcode: String) {
        val isValid = authenticationService.isValid(account, passcode)
        assertTrue(isValid)
    }

    private fun givenToken(token: String) {
        every { tokenDao.getRandom(any()) } returns token
    }

    private fun givenPassword(account: String, password: String) {
        every { profile.getPassword(account) } returns password
    }
}

