open class ProfileDao {
    open fun getPassword(account: String): String? {
        return Context.getPassword(account)
    }

}
