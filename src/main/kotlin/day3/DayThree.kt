package day3

import java.io.FileInputStream

class DayThree {

    fun solve(){
        val calculator = Calculator.fromInput("src/main/resources/day3/input.txt")
        println("Result: ${calculator.calculateAllMultiplications()}")
        println("Filtered Result: ${calculator.calculateFilteredMultiplications()}")
    }

    class Calculator(private val inputFilter: InputFilter) {
        companion object {
            fun fromInput(inputPath: String): Calculator {
                return Calculator(InputFilter.fromPath(inputPath))
            }
        }

        fun calculateAllMultiplications(): Int {
            return inputFilter.allMulValues.map { it.first * it.second }.sum()
        }

        fun calculateFilteredMultiplications(): Int {
            return inputFilter.filteredMulValues.map { it.first * it.second }.sum()
        }
    }

    class InputFilter(private val input: String) {
        companion object {
            val MUL_PATTERN = Regex("""mul\(\d{1,3},\d{1,3}\)""")
            val DO_PATTERN = Regex("do()")
            val DONT_PATTERN = Regex("don't()")

            fun fromPath(path: String): InputFilter {
                return fromString(InputReader.fromPath(path).read())
            }

            private fun fromString(input: String): InputFilter {
                return InputFilter(input)
            }
        }

        val allMulValues by lazy { getMulValues(sequenceAllMulPatterns().map { it.value }) }
        val filteredMulValues by lazy { getMulValues(getFilteredMulPatterns().map { it.value }) }

        private fun getMulValues(mulPatterns: Sequence<String>): Sequence<Pair<Int, Int>> {
            return mulPatterns.map { mulPattern ->
                val values = mulPattern.removePrefix("mul(").removeSuffix(")").split(",")
                Pair(values[0].toInt(), values[1].toInt())
            }
        }

        private fun getFilteredMulPatterns(): Sequence<MatchResult> {
            val allMatchersSorted = listOf(MUL_PATTERN, DO_PATTERN, DONT_PATTERN)
                .map { it.findAll(input) }
                .flatMap { it }
                .sortedBy { it.range.first }

            var isEnabled = true
            val filteredMulMatchers = mutableListOf<MatchResult>()
            allMatchersSorted.forEach { matcher ->
                when {
                    MUL_PATTERN.matches(matcher.value) -> if(isEnabled) filteredMulMatchers.add(matcher)
                    DO_PATTERN.matches(matcher.value) -> isEnabled = true
                    DONT_PATTERN.matches(matcher.value) -> isEnabled = false
                }
            }
            return filteredMulMatchers.asSequence()
        }

        private fun sequenceAllMulPatterns(): Sequence<MatchResult> {
            return MUL_PATTERN.findAll(input)
        }
    }

    class InputReader(private val input: FileInputStream) {
        companion object {
            fun fromPath(path: String): InputReader {
                return InputReader(FileInputStream(path))
            }
        }

        fun read(): String {
            return String(input.readAllBytes()).replace("\n", "")
        }
    }
}
