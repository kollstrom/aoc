package no.kollstrom.aoc

import no.kollstrom.aoc.OpCode.*

// TODO: Implement multiline intcode programs
// TODO: Candidate for recursion?

typealias Code = Int

@ExperimentalStdlibApi
object IntCodeCalculator {
    fun calculate(input: List<Int>): List<Int> {
        val processed = input.splitIntoOperations()
                .asSequence()
                .takeWhile { it.opCode() != HALT }
                .map { op ->
                    val inputOne = op[op[1]]
                    val inputTwo = op[op[2]]
                    val calculation = when (op.opCode()) {
                        ADD -> inputOne + inputTwo
                        MULTIPLY -> inputOne * inputTwo
                        else -> throw InvalidOpCodeException("That operation code is invalid", op.first())
                    }
                    val outputPosition = op[3]
                    op.mapIndexed { index, value -> if (index == outputPosition) calculation else value }
                }.flatten().toList()
        return processed + input.slice(processed.size until input.size)
    }
}

fun List<Int>.opCode(): OpCode = OpCode.getByCode(this.first())

@ExperimentalStdlibApi
fun List<Int>.splitIntoOperations(): List<List<Int>> {
    return foldIndexed(mutableListOf<MutableList<Int>>()) { index, operations, value ->
        if (index % 4 == 0) {
            operations.add(mutableListOf(value))
        } else {
            val last = operations.removeLast()
            last.add(value)
            operations.add(last)
        }
        operations
    }
}

data class InvalidOpCodeException(
        override val message: String = "That is not a valid operation code!",
        val code: Int
) : Exception(message)

enum class OpCode(val code: Int) {
    ADD(1),
    MULTIPLY(2),
    HALT(99),
    INVALID(-1)
    ;

    companion object {
        private val values = values();
        fun getByCode(code: Int) = values.firstOrNull { it.code == code } ?: INVALID
    }
}