import java.util.*

class RsaTokenDao {
    fun getRandom(account: String?): String {
        val seed = Random((System.currentTimeMillis().toInt() and 0x0000FFFF).toLong())
        val result = String.format("%06d", seed.nextInt(999999))
        println(String.format("randomCode:%s", result))
        return result
    }
}