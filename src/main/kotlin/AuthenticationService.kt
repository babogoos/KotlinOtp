class AuthenticationService(
    private val profile: ProfileDao = ProfileDao(),
    private val token: RsaTokenDao = RsaTokenDao()
) {

    fun isValid(account: String, passcode: String): Boolean {
        val passwordFromDao = profile.getPassword(account)

        val randomCode = token.getRandom(account)

        val validPassCode = passwordFromDao + randomCode

        val isValid = passcode == validPassCode
        if (isValid) {

            return true
        }

        return false
    }
}