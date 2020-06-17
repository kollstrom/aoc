package no.kollstrom.aoc

// https://adventofcode.com/2019/day/1

const val DIVIDE_BY = 3
const val SUBTRACT_BY = 2

data class Module(val mass: Int) {
    fun calculateRequiredFuel(): Int = mass.div(DIVIDE_BY) - SUBTRACT_BY
}