package no.kollstrom.aoc

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class ModuleTest {

    private fun massCalculationTest(mass: Int, expected: Int) {
        val module = Module(mass)
        assertEquals(expected, module.calculateRequiredFuel())
    }

    @Test
    internal fun `mass of 12 requires 2 fuel`() {
        massCalculationTest(12, 2)
    }

    @Test
    internal fun `mass of 14 requires 2 fuel`() {
        massCalculationTest(14, 2)
    }

    @Test
    internal fun `mass of 1969 requires 654 fuel`() {
        massCalculationTest(1969, 654)
    }

    @Test
    internal fun `mass of 100756 requires 33583 fuel`() {
        massCalculationTest(100756, 33583)
    }
}