class AuthenticationService {
    fun isValid(account: String, passcode: String): Boolean {
        val profileDao = ProfileDao()
        val passwordFromDao = profileDao.getPassword(account)

        val rsaTokenDao = RsaTokenDao()
        val randomCode = rsaTokenDao.getRandom(account)

        val validPassCode = passwordFromDao + randomCode

        val isValid = passcode == validPassCode
        if (isValid) {

            return true
        }

        return false
    }
}