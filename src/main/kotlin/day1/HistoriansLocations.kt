package day1

import kotlin.math.abs

class HistoriansLocations(
    private val sortedLocations1: List<Int>,
    private val sortedLocations2: List<Int>,
) {
    //this cannot be Map, as keys are a set, and we can get duplicated values within locations1
    private val locations1OccurrencesInLocations2: List<Int> by lazy {
        val locationCounts = sortedLocations2.groupingBy { it }.eachCount()
        sortedLocations1.map { location1 ->
            locationCounts[location1] ?: 0
        }
    }

    val totalSimilarityScore: Int by lazy { similaritiesScores().sum() }

    val totalDistance: Int by lazy { calculateDistances().sum() }

    companion object {
        fun from(filePath: String): HistoriansLocations {
            val input = Input.from(filePath)
            return HistoriansLocations(
                input.group1.sorted(),
                input.group2.sorted()
            )
        }
    }

    private fun similaritiesScores(): List<Int> =
        sortedLocations1.zip(locations1OccurrencesInLocations2)
            .map { (location, count) -> similarityScore(location, count) }
            .toList()

    private fun similarityScore(location: Int, count: Int): Int = location * count

    private fun calculateDistances(): List<Int> {
        return sortedLocations1.zip(sortedLocations2)
            .map { calculateDistance(it.first, it.second) }
    }

    private fun calculateDistance(location1: Int, location2: Int): Int {
        return abs(location1 - location2)
    }
}