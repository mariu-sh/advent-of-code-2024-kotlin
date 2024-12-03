package day3

import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

class DayThreeTest {

    @Test
    fun shouldCalculate() {
        val calculator = DayThree.Calculator.fromInput("src/test/resources/day3/testInput.txt")

        val result = calculator.calculate()

        assertThat(result).isEqualTo(161)
    }
}