package org.example.Day1

import Day1.Input
import kotlin.math.abs

class DayOne {

    fun solve() {
        val totalDistance = HistoriansLocations.from(Input().read()).totalDistance()
        println(totalDistance)
    }

    data class HistoriansLocations(
        val sortedLocations1: List<Int>,
        val sortedLocations2: List<Int>
    ) {
        companion object {
            fun from(input: Input): HistoriansLocations {
                return HistoriansLocations(
                    input.group1.sorted(),
                    input.group2.sorted()
                )
            }
        }

        fun totalDistance(): Int {
            return calculateDistances().sum()
        }

        private fun calculateDistances(): List<Int> {
            return sortedLocations1.zip(sortedLocations2).map { calculateDistance(it.first, it.second) }
        }

        private fun calculateDistance(location1: Int, location2: Int): Int {
            return abs(location1 - location2)
        }
    }

    fun testSolution() {
        val input = Input(
            listOf(3, 4, 2, 1, 3, 3),
            listOf(4, 3, 5, 3, 9, 3)
        )

        val result = HistoriansLocations.from(input).totalDistance()
        assert(result.equals(11))
    }

}