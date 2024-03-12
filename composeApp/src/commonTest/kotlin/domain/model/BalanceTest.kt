package domain.model

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

internal class BalanceTest {
    private lateinit var balance: Balance

    @Test
    fun `Balance fails with negative value`() {
        assertFailsWith<IllegalArgumentException>(message = "Value can't be negative") {
            balance = Balance(-100L)
        }
    }

    @Test
    fun `Balance with constructor zero`() {
        balance = Balance(0L)
        assertEquals("0.00", balance.toString())
    }

    @Test
    fun `Balance constructor with decimal zero`() {
        balance = Balance(123_456_789_00L)
        assertEquals("123 456 789.00", balance.toString())
    }

    @Test
    fun `Balance constructor with decimal not zero`() {
        balance = Balance(123_456_789_12L)
        assertEquals("123 456 789.12", balance.toString())
    }

    @Test
    fun `Balance constructor with single digit`() {
        balance = Balance(1L)
        assertEquals("0.01", balance.toString())
    }

    @Test
    fun `Balance constructor with trillion monetary value`() {
        balance = Balance(1_234_567_890_123_45L)
        assertEquals("1 234 567 890 123.45", balance.toString())
    }

    @Test
    fun `Balance fromString fails with negative value`() {
        assertFailsWith<IllegalArgumentException>(message = "Value can't be negative") {
            balance = Balance.fromString("-100")
        }
    }

    @Test
    fun `Balance fromString with empty string return balance zero`() {
        balance = Balance.fromString("")
        assertEquals("0.00", balance.toString())
    }

    @Test
    fun `Balance fromString with decimal zero`() {
        balance = Balance.fromString("123 456 789.00")
        assertEquals("123 456 789.00", balance.toString())
    }

    @Test
    fun `Balance fromString with decimal not zero`() {
        balance = Balance.fromString("123 456 789.12")
        assertEquals("123 456 789.12", balance.toString())
    }

    @Test
    fun `Balance fromString with trillion monetary value`() {
        balance = Balance.fromString("1 234 567 890 123.45")
        assertEquals("1 234 567 890 123.45", balance.toString())
    }

    @Test
    fun `Balance plus operator`() {
        balance = Balance(100L)
        assertEquals("1.00", balance.toString())
        val newBalance = balance + 50
        assertEquals("1.50", newBalance.toString())
    }

    @Test
    fun `Balance minus operator`() {
        balance = Balance(100L)
        assertEquals("1.00", balance.toString())
        val newBalance = balance - 50
        assertEquals("0.50", newBalance.toString())
    }

    @Test
    fun `Balance minus operator assert value can't be negative`() {
        balance = Balance(100L)
        assertEquals("1.00", balance.toString())
        val newBalance = balance - 150
        assertEquals("0.00", newBalance.toString())
    }

}
