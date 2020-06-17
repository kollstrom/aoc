package no.kollstrom.aoc

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException

internal class IntCodeCalculatorTest {
    @Test
    internal fun `1,0,0,0,99 becomes 2,0,0,0,99`() {
        val input = listOf(1, 0, 0, 0, 99)
        val expected = listOf(2, 0, 0, 0, 99)
        assertEquals(expected, IntCodeCalculator.calculate(input))
    }

    @Test
    internal fun `2,3,0,3,99 becomes 2,3,0,6,99`() {
        val input = listOf(2, 3, 0, 3, 99)
        val expected = listOf(2, 3, 0, 6, 99)
        assertEquals(expected, IntCodeCalculator.calculate(input))
    }

    @Test
    internal fun `2,4,4,5,99,0 becomes 2,4,4,5,99,9801`() {
        val input = listOf(2, 4, 4, 5, 99, 0)
        val expected = listOf(2, 4, 4, 5, 99, 9801)
        assertEquals(expected, IntCodeCalculator.calculate(input))
    }

    @Test
    internal fun `1,1,1,4,99,5,6,0,99 becomes 30,1,1,4,2,5,6,0,99`() {
        val input = listOf(1, 1, 1, 4, 99, 5, 6, 0, 99)
        val expected = listOf(30, 1, 1, 4, 2, 5, 6, 0, 99)
        assertEquals(expected, IntCodeCalculator.calculate(input))
    }

    @Test
    internal fun `Non-opcode crashes program`() {
        val input = listOf(23, 34, 45)
        assertThrows<InvalidOpCodeException> ("Should throw an exception") {
            IntCodeCalculator.calculate(input)
        }
    }
}