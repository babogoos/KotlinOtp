import io.mockk.every
import io.mockk.mockk
import junit.framework.Assert.assertTrue
import org.junit.Test

class AuthenticationServiceTest {

    @Test
    fun is_valid() {
        val profile = mockk<ProfileDao>()
        every { profile.getPassword("joey") } returns "91"

        val tokenDao = mockk<RsaTokenDao>()
        every { tokenDao.getRandom(any()) } returns "000000"

        val authenticationService = AuthenticationService(profile, tokenDao)

        val isValid = authenticationService.isValid("joey", "91000000")

        assertTrue(isValid)
    }
}

