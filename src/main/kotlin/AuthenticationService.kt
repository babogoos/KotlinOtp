class AuthenticationService(
    private val profile: ProfileDao = ProfileDao(),
    private val token: RsaTokenDao = RsaTokenDao(),
    private val logger: MyLogger
) {

    fun isValid(account: String, passcode: String): Boolean {
        val passwordFromDao = profile.getPassword(account)

        val randomCode = token.getRandom(account)

        val validPassCode = passwordFromDao + randomCode

        val isValid = passcode == validPassCode
        if (isValid) {

            return true
        } else {

            logger.save("account: $account try to login failed")
            return false
        }

    }
}