package org.example.day8

import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

class DayEightTest {
    val testInputPath = "src/test/resources/day8/testInput.txt"
    val realInputPath = "src/main/resources/day8/input.txt"

    @Test
    fun shouldCountAllAntinodes(){
        val result = DayEight(testInputPath).solve()
        assertThat(result.first).isEqualTo(14)
    }
}