class ProfileDao {
    fun getPassword(account: String): String? {
        return Context.getPassword(account)
    }

}
