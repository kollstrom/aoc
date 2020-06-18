package no.kollstrom.aoc

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException
import kotlin.math.exp

@ExperimentalStdlibApi
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
    internal fun `Non-opcode crashes program`() {
        val input = listOf(23, 0, 1)
        assertThrows<InvalidOpCodeException> ("Should throw an exception") {
            IntCodeCalculator.calculate(input)
        }
    }

    @Test
    internal fun `splits input into 3 operation lists`() {
        val input = listOf(1, 2, 3, 4, 1, 2, 3, 4, 1, 2, 3, 4)
        assertEquals(3, input.splitIntoOperations().count())
    }

    @Test
    internal fun `all input operations are split into groups of 4`() {
        val input = listOf(1, 2, 3, 4, 1, 2, 3, 4, 1, 2, 3, 4)
        val expected = listOf(1, 2, 3, 4)
        val actual = input.splitIntoOperations()
        assertEquals(expected, actual[0])
        assertEquals(expected, actual[1])
        assertEquals(expected, actual[2])
    }
}