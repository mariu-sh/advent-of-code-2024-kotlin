package day3

import org.assertj.core.api.Assertions.assertThat
import org.example.day3.Calculator
import org.example.day3.InputFilter
import org.junit.jupiter.api.Nested
import kotlin.test.Test

class DayThreeTest {

    val calculator = Calculator()

    @Nested
    inner class CalculatorTest {
        @Test
        fun shouldCalculateAll() {
            val inputPath = "src/test/resources/day3/testInput.txt"

            val values = InputFilter.from(inputPath).filterAllMulValues()

            assertThat(calculator.calculate(values)).isEqualTo(161)
        }

        @Test
        fun shouldCalculateLimited() {
            val inputPath = "src/test/resources/day3/testInputPart2.txt"

            val values = InputFilter.from(inputPath).filterLimitedMulValues()

            assertThat(calculator.calculate(values)).isEqualTo(48)
        }
    }

    @Nested
    inner class InputFilterTest {
        @Test
        fun shouldFilterAll() {
            val inputPath = "src/test/resources/day3/testInput.txt"

            val values = InputFilter.from(inputPath).filterAllMulValues()

            assertThat(values.toList().size).isEqualTo(4)
        }

        @Test
        fun shouldFilterLimited() {
            val inputPath = "src/test/resources/day3/testInputPart2.txt"

            val values = InputFilter.from(inputPath).filterLimitedMulValues()

            assertThat(values.toList().size).isEqualTo(2)
        }
    }
}