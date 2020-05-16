import kotlin.collections.HashMap

object Context {
    var profiles: HashMap<String, String>? = null
    fun getPassword(key: String): String? {
        return profiles!![key]
    }

    init {
        profiles = HashMap()
        profiles!!["joey"] = "91"
        profiles!!["mei"] = "99"
    }
}