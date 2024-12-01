package day1

import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

class DayOneTest {

    private val testInputFilePath = "src/test/resources/day1/testInput.csv"

    @Test
    fun shouldCalculateTotalDistance() {
        val historiansLocations = HistoriansLocations.from(testInputFilePath)

        val totalDistance = historiansLocations.totalDistance
        assertThat(totalDistance).isEqualTo(11)
    }

    @Test
    fun shouldCalculateSimilarityScore() {
        val historiansLocations = HistoriansLocations.from(testInputFilePath)

        val similarityScore = historiansLocations.totalSimilarityScore
        assertThat(similarityScore).isEqualTo(31)
    }
}