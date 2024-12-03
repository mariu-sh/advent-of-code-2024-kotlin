package day3

import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

class DayThreeTest {

    @Test
    fun shouldCalculate() {
        val calculator = DayThree.Calculator.fromInput("src/test/resources/day3/testInput.txt")

        val result = calculator.calculateAllMultiplications()

        assertThat(result).isEqualTo(161)
    }

    @Test
    fun shouldCalculateFiltered() {
        val calculator = DayThree.Calculator.fromInput("src/test/resources/day3/testInput2.txt")

        val result = calculator.calculateFilteredMultiplications()

        assertThat(result).isEqualTo(48)
    }
}