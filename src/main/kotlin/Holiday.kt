import java.time.LocalDate

/**
 * Created by dion on 2021/04/10.
 */

// SPEC: today is Xmas?
//       true -> Merry Christmas,
//       false -> Today is not Christmas.

// SPEC V2: 12/24 is Xmas too,
//       true -> Merry Christmas,
//       false -> Today is not Christmas.

open class Holiday() {

    fun isTodayXmas(): String {
        val today = getNow()
        return if (today.monthValue == 12 && (today.dayOfMonth == 25 || today.dayOfMonth == 24))
            "Merry Xmas"
        else
            "Today is not Xmas"
    }

    open fun getNow() = LocalDate.now()
}



