package org.example.day5

import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

class DayFiveTest{
    val testInputFilePath = "src/test/resources/day5/testInput.txt"
    val inputFilePath = "src/main/resources/day5/input.txt"

    @Test
    fun shouldSumMiddleItemInCorrectlyOrderedUpdates() {
        val result = DayFive(testInputFilePath).solve()
        assertThat(result.first).isEqualTo(143)
        assertThat(result.second).isEqualTo(123)
    }

    @Test
    fun shouldSumMiddleItemInCorrectlyOrderedUpdatesFromInputFile() {
        val result = DayFive(inputFilePath).solve()
        assertThat(result.first).isEqualTo(4637)
        assertThat(result.second).isEqualTo(6370)
    }
}