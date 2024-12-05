package org.example.day5

import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

class DayFiveTest {
    val testInputFilePath = "src/test/resources/day5/testInput.txt"
    val inputFilePath = "src/main/resources/day5/input.txt"

    @Test
    fun shouldSumMiddleItems() {
        val result = DayFive(testInputFilePath).solve()
        assertThat(result.first).isEqualTo(143)
        assertThat(result.second).isEqualTo(123)
    }

    @Test
    fun shouldSumMiddleItemsFromInputFile() {
        val result = DayFive(inputFilePath).solve()
        assertThat(result.first).isEqualTo(4637)
        assertThat(result.second).isEqualTo(6370)
    }
}