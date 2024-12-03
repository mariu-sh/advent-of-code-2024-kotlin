package day1

import org.assertj.core.api.Assertions.assertThat
import org.example.day1.HistoriansLocations
import kotlin.test.Test

class DayOneTest {

    private val testInputFilePath = "src/test/resources/day1/testInput.csv"
    private val inputFliePath = "src/main/resources/day1/input.csv"

    @Test
    fun shouldCalculateTotalDistance() {
        val historiansLocations = HistoriansLocations.from(testInputFilePath)

        val totalDistance = historiansLocations.totalDistance
        assertThat(totalDistance).isEqualTo(11)
    }

    @Test
    fun shouldCalculateTotalDistanceFromInputFile() {
        val historiansLocations = HistoriansLocations.from(inputFliePath)

        val totalDistance = historiansLocations.totalDistance
        assertThat(totalDistance).isEqualTo(1834060)
    }

    @Test
    fun shouldCalculateSimilarityScore() {
        val historiansLocations = HistoriansLocations.from(testInputFilePath)

        val similarityScore = historiansLocations.totalSimilarityScore
        assertThat(similarityScore).isEqualTo(31)
    }

    @Test
    fun shouldCalculateSimilarityScoreFromInputFile() {
        val historiansLocations = HistoriansLocations.from(inputFliePath)

        val similarityScore = historiansLocations.totalSimilarityScore
        assertThat(similarityScore).isEqualTo(21607792)
    }
}