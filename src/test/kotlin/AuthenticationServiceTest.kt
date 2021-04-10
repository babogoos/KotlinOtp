import junit.framework.Assert.assertSame
import org.junit.Before
import org.junit.Test
import java.time.LocalDate

class AuthenticationServiceTest {

    private val mockHoliday = MockHoliday()

    @Before
    fun before() {

    }

    @Test
    fun `test Today is Xmas`() {
        // Arrange -> Given
        givenToday(12, 25)
        // Act -> When
        // Assert -> then, should
        val result = mockHoliday.isTodayXmas()
        assertSame("Merry Xmas", result)
    }

    @Test
    fun `test Today is Xmas when Dec 24th`() {
        // Arrange -> Given
        givenToday(12, 24)
        // Act -> When
        // Assert -> then, should
        val result = mockHoliday.isTodayXmas()
        assertSame("Merry Xmas", result)
    }

    @Test
    fun `test Today is Xmas when Nov 24th`() {
        // Arrange -> Given
        givenToday(11, 24)
        // Act -> When
        // Assert -> then, should
        val result = mockHoliday.isTodayXmas()
        assertSame("Today is not Xmas", result)
    }

    @Test
    fun `test Today is not Xmas`() {
        // Arrange -> Given
        givenToday(11, 25)
        // Act -> When
        // Assert -> then, should
        val result = mockHoliday.isTodayXmas()
        assertSame("Today is not Xmas", result)
    }

    private fun givenToday(month: Int, date: Int) {
        mockHoliday.setToday(month, date)
    }

}

class MockHoliday : Holiday() {

    private lateinit var today: LocalDate

    fun setToday(month: Int, date: Int) {
        today = LocalDate.of(2021, month, date)
    }

    override fun getNow(): LocalDate {
        return today
    }
}