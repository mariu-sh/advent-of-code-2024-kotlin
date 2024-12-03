package day3

import org.assertj.core.api.Assertions.assertThat
import org.example.day3.Calculator
import org.example.day3.InputFilter
import org.junit.jupiter.api.Nested
import kotlin.test.Test

class DayThreeTest {

    val calculator = Calculator()
    val testInputPathPart1 = "src/test/resources/day3/testInput.txt"
    val testInputPathPart2 = "src/test/resources/day3/testInputPart2.txt"
    val inputPath = "src/main/resources/day3/input.txt"

    @Nested
    inner class CalculatorTest {
        @Test
        fun shouldCalculateAll() {
            val values = InputFilter.from(testInputPathPart1).filterAllMulValues()

            assertThat(calculator.calculate(values)).isEqualTo(161)
        }

        @Test
        fun shouldCalculateAllFromInputFile() {
            val values = InputFilter.from(inputPath).filterAllMulValues()

            assertThat(calculator.calculate(values)).isEqualTo(174960292)
        }

        @Test
        fun shouldCalculateLimited() {
            val values = InputFilter.from(testInputPathPart2).filterLimitedMulValues()

            assertThat(calculator.calculate(values)).isEqualTo(48)
        }

        @Test
        fun shouldCalculateLimitedFromInputFile() {
            val values = InputFilter.from(inputPath).filterLimitedMulValues()

            assertThat(calculator.calculate(values)).isEqualTo(56275602)
        }
    }

    @Nested
    inner class InputFilterTest {
        @Test
        fun shouldFilterAll() {
            val values = InputFilter.from(testInputPathPart1).filterAllMulValues()

            assertThat(values.toList().size).isEqualTo(4)
        }

        @Test
        fun shouldFilterAllFromInputFile() {
            val values = InputFilter.from(inputPath).filterAllMulValues()

            assertThat(values.toList().size).isEqualTo(686)
        }

        @Test
        fun shouldFilterLimited() {
            val values = InputFilter.from(testInputPathPart2).filterLimitedMulValues()

            assertThat(values.toList().size).isEqualTo(2)
        }

        @Test
        fun shouldFilterLimitedFromInputFile() {
            val values = InputFilter.from(inputPath).filterLimitedMulValues()

            assertThat(values.toList().size).isEqualTo(226)
        }
    }
}