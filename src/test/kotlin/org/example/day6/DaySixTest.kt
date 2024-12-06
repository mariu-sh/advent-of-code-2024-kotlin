package org.example.day6

import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

class DaySixTest {

    val testInputPath: String = "src/test/resources/day6/testInput.txt"

    @Test
    fun shouldSolvePartOne(){
        val result = DaySix(testInputPath).solve()

        assertThat(result.first).isEqualTo(41)
    }

    @Test
    fun shouldSolvePartOneFromInput() {
        val result = DaySix().solve()

        assertThat(result.first).isEqualTo(5153)
    }
}