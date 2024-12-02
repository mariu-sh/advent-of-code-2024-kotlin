package day2

import org.assertj.core.api.Assertions.assertThat
import org.example.day2.DayTwo.Report
import org.example.day2.DayTwo.TextInput
import kotlin.test.Test

class DayTwoTest {

    private val testInputFilePath = "src/test/resources/day2/testInput.txt"

    @Test
    fun shouldCalculateSafeReports() {
        val reports: List<Report> = TextInput.fromPath(testInputFilePath)
            .getReportsValues()
            .map { Report(it) }
        val safeReports = reports.filter { it.isSafe() }
        assertThat(safeReports).hasSize(2)
    }
}