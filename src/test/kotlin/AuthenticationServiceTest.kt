import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class AuthenticationServiceTest {

    private val profile = mockk<ProfileDao>(relaxed = true)
    private val tokenDao = mockk<RsaTokenDao>(relaxed = true)
    private val logger = mockk<MyLogger>(relaxed = true)
    private val authenticationService = AuthenticationService(profile, tokenDao, logger)

    @Test
    fun is_valid() {
        givenPassword("joey", "91")
        givenToken("000000")
        shouldBeValid("joey", "91000000")
    }

    @Test
    fun is_invalid() {
        givenPassword("joey", "91")
        givenToken("000000")
        shouldBeInvalid("joey", "wrong passcode")
    }

    @Test
    fun should_log_account_when_invalid() {
        whenInvalid("joey", "wrong passcode")
        shouldLog("joey", "failed")
    }

    @Test
    fun should_not_log_when_valid() {
        givenPassword("joey", "91")
        givenToken("000000")
        authenticationService.isValid("joey", "91000000")
        shouldNotLog()
    }

    private fun shouldNotLog() {
        verify(exactly = 0) {
            logger.save(any())
        }
    }

    private fun shouldLog(account: String, state: String) {
        //        verify { logger.save("account: joey try to login failed") }
        //        verify { logger.save(any()) }
        //        verify(exactly = 1) { logger.save(any()) }
        val message = slot<String>()
        verify(exactly = 1) {
            logger.save(capture(message))
        }

        assertThat(message.captured).contains(account, state)
    }

    private fun whenInvalid(account: String, passcode: String) {
        givenPassword(account, "91")
        givenToken("000000")
        authenticationService.isValid(account, passcode)
    }

    private fun shouldBeInvalid(account: String, passcode: String) {
        assertFalse(authenticationService.isValid(account, passcode))
    }

    private fun shouldBeValid(account: String, passcode: String) {
        assertTrue(authenticationService.isValid(account, passcode))
    }

    private fun givenToken(token: String) {
        every { tokenDao.getRandom(any()) } returns token
    }

    private fun givenPassword(account: String, password: String) {
        every { profile.getPassword(account) } returns password
    }
}

