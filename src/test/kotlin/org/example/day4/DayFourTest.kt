package org.example.day4

import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

class DayFourTest {
    private val testInputFilePath = "src/test/resources/day4/testInput.txt"
    private val inputFilePath = "src/main/resources/day4/input.txt"

    @Test
    fun shouldCalculateTotalXMASOccurrences() {
        val result = DayFour(testInputFilePath).solvePartOne()

        assertThat(result).isEqualTo(18)
    }

    @Test
    fun shouldCalculateTotalXMASOccurrencesFromInputFile() {
        val result = DayFour(inputFilePath).solvePartOne()

        assertThat(result).isEqualTo(2571)
    }

    @Test
    fun shouldCalculateXmasShapeOccurrences(){
        val result = DayFour(testInputFilePath).solvePartTwo()

        assertThat(result).isEqualTo(9)
    }

    @Test
    fun shouldCalculateXmasShapeOccurrencesFromInputFile(){
        val result = DayFour(inputFilePath).solvePartTwo()

        assertThat(result).isEqualTo(1992)
    }
}