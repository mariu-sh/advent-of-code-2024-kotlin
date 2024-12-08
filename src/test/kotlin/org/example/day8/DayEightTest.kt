package org.example.day8

import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

class DayEightTest {
    private val testInputPath = "src/test/resources/day8/testInput.txt"
    private val testInputSimplifiedPath = "src/test/resources/day8/testInputSimplified.txt"
    private val realInputPath = "src/main/resources/day8/input.txt"

    @Test
    fun shouldCountAllAntinodes(){
        val result = DayEight(testInputPath).solve()
        assertThat(result.first).isEqualTo(14)
    }

    @Test
    fun shouldCountAllAntinodesFromRealInput(){
        val result = DayEight(realInputPath).solve()
        assertThat(result.first).isEqualTo(265)
    }

    @Test
    fun shouldCountAllHarmonicAntinodesFromSimplifiedTestInput(){
        val result = DayEight(testInputSimplifiedPath).solve()
        assertThat(result.second).isEqualTo(9)
    }

    @Test
    fun shouldCountAllHarmonicAntinodes(){
        val result = DayEight(testInputPath).solve()
        assertThat(result.second).isEqualTo(34)
    }

    @Test
    fun shouldCountAllHarmonicAntinodesFromRealInput(){
        val result = DayEight(realInputPath).solve()
        assertThat(result.second).isEqualTo(962)
    }
}