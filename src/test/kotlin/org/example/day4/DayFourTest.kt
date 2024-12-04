package org.example.day4

import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

class DayFourTest {
    val testInputFilePath = "src/test/resources/day4/testInput.txt"
    val inputFilePath = "src/main/resources/day4/input.txt"

    @Test
    fun shouldCalculateTotalXMASOccurences() {
        val dayFour = DayFour(testInputFilePath)

        val result = dayFour.solvePartOne()

        assertThat(result).isEqualTo(18)
    }

    @Test
    fun shouldCalculateTotalXMASOccurencesFromInputFile() {
        val dayFour = DayFour(inputFilePath)

        val result = dayFour.solvePartOne()

        assertThat(result).isEqualTo(2571)
    }

    @Test
    fun `shouldCalculateX-MASOccurences`(){
        val dayFour = DayFour(testInputFilePath)

        val result = dayFour.solvePartTwo()

        assertThat(result).isEqualTo(9)
    }
}