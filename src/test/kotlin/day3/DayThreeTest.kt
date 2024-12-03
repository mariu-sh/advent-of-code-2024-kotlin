package day3

import org.assertj.core.api.Assertions.assertThat
import org.example.day3.Calculator
import org.example.day3.Filter
import org.junit.jupiter.api.Nested
import kotlin.test.Test

class DayThreeTest {

    val calculator = Calculator()
    val testInputPath = "src/test/resources/day3/testInput.txt"
    val inputPath = "src/main/resources/day3/input.txt"

    @Nested
    inner class CalculatorTest {
        @Test
        fun shouldCalculateAll() {
            val values = Filter.from(this@DayThreeTest.testInputPath).filterAllMulValues()

            assertThat(calculator.calculate(values)).isEqualTo(161)
        }

        @Test
        fun shouldCalculateAllFromInputFile() {
            val values = Filter.from(inputPath).filterAllMulValues()

            assertThat(calculator.calculate(values)).isEqualTo(174960292)
        }

        @Test
        fun shouldCalculateLimited() {
            val values = Filter.from(this@DayThreeTest.testInputPath).filterLimitedMulValues()

            assertThat(calculator.calculate(values)).isEqualTo(48)
        }

        @Test
        fun shouldCalculateLimitedFromInputFile() {
            val values = Filter.from(inputPath).filterLimitedMulValues()

            assertThat(calculator.calculate(values)).isEqualTo(56275602)
        }
    }

    @Nested
    inner class FilterTest {
        @Test
        fun shouldFilterAll() {
            val values = Filter.from(this@DayThreeTest.testInputPath).filterAllMulValues()

            assertThat(values.toList().size).isEqualTo(4)
        }

        @Test
        fun shouldFilterAllFromInputFile() {
            val values = Filter.from(inputPath).filterAllMulValues()

            assertThat(values.toList().size).isEqualTo(686)
        }

        @Test
        fun shouldFilterLimited() {
            val values = Filter.from(this@DayThreeTest.testInputPath).filterLimitedMulValues()

            assertThat(values.toList().size).isEqualTo(2)
        }

        @Test
        fun shouldFilterLimitedFromInputFile() {
            val values = Filter.from(inputPath).filterLimitedMulValues()

            assertThat(values.toList().size).isEqualTo(226)
        }
    }
}