package org.example.day1

class DayOne {

    private val inputCsvPath = "src/main/resources/day1/input.csv"

    fun solve() {
        val historiansLocations = HistoriansLocations.from(inputCsvPath)
        println("Total distance: ${historiansLocations.totalDistance}")
        println("Total similarity score: ${historiansLocations.totalSimilarityScore}")
    }
}