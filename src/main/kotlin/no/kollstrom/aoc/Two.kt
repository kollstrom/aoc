package no.kollstrom.aoc

typealias Code = Int

object IntCodeCalculator {
    fun calculate(input: List<Int>): List<Int> {
        val opCode = input.opCode()
        return emptyList()
    }

}

fun List<Code>.opCode(): OpCode = OpCode.getByCode(this.first())

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