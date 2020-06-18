package no.kollstrom.aoc

// TODO: Implement multiline intcode programs
// TODO: Candidate for recursion?

typealias Code = Int

object IntCodeCalculator {
    fun calculate(input: List<Int>): List<Int> {
        val opCode = input.opCode()
        val inputOne = input[1]
        val inputTwo = input[2]
        val outputPosition = input[3]
        return emptyList()
    }
}

fun List<Int>.opCode(): OpCode = OpCode.getByCode(this.first())

@ExperimentalStdlibApi
fun List<Int>.splitIntoOperations(): List<List<Int>> {
    // TODO: Make this simpler. Maybe split out a fold version and one simpler one
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