package org.example.day7

import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

class DaySevenTest{
    private val testInputPath = "src/test/resources/day7/testInput.txt"
    private val realInputPath = "src/main/resources/day7/input.txt"

    @Test
    fun shouldSolvePartOne(){
        val result = DaySeven(testInputPath).solve()
        assertThat(result.first).isEqualTo(3749L)
    }

    @Test
    fun shouldSolvePartOneFromRealInput(){
        val result = DaySeven(realInputPath).solve()
        assertThat(result.first).isEqualTo(1153997401072L)
    }

    @Test
    fun shouldSolvePartTwo(){
        val result = DaySeven(testInputPath).solve()
        assertThat(result.second).isEqualTo(11387L)
    }
}