package org.example.day5

import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

class DayFiveTest{
    val testInputFilePath = "src/test/resources/day5/testInput.txt"
    val inputFilePath = "src/main/resources/day5/input.txt"

    @Test
    fun shouldSumMiddleItemInCorrectlyOrderedUpdates() {
        val result = DayFive(testInputFilePath).solvePartOne()
        assertThat(result).isEqualTo(143)
    }
}