package day3

import java.io.FileInputStream

class DayThree {

    fun solve(){
        val calculator = Calculator.fromInput("src/main/resources/day3/input.txt")
        println("Result: ${calculator.calculate()}")
    }

    class Calculator(val input: Sequence<Pair<Int, Int>>) {
        companion object {
            fun fromInput(inputPath: String): Calculator {
                return Calculator(InputFilter.fromPath(inputPath).sequenceValues())
            }
        }

        fun calculate(): Int {
            return input.map { it.first * it.second }.sum()
        }
    }

    class InputFilter(val input: String) {
        companion object {
            val PATTERN = Regex("""mul\(\d{1,3},\d{1,3}\)""")

            fun fromPath(path: String): InputFilter {
                return fromString(InputReader.fromPath(path).read())
            }

            private fun fromString(input: String): InputFilter {
                return InputFilter(input)
            }
        }

        fun sequenceValues(): Sequence<Pair<Int, Int>> {
            return sequencePatterns().map { mulPattern ->
                val values = mulPattern.removePrefix("mul(").removeSuffix(")").split(",")
                Pair(values[0].toInt(), values[1].toInt())
            }
        }

        fun sequencePatterns(): Sequence<String> {
            return PATTERN.findAll(input).map { it.value }
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
