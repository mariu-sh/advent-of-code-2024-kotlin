package day2

import org.assertj.core.api.Assertions.assertThat
import org.example.day2.Report
import org.example.day2.Report.Reports
import org.example.shared.TextFileInput
import kotlin.test.Test

class DayTwoTest {

    private val testInputFilePath = "src/test/resources/day2/testInput.txt"
    private val inputFilePath = "src/main/resources/day2/input.txt"

    @Test
    fun shouldCalculateSafeReportsWithoutDampening() {
        val reports: List<Report> = readReportsFromFile(testInputFilePath)
        val safeReports = reports.filter { it.isBasicSafe() }
        assertThat(safeReports).hasSize(2)
    }

    @Test
    fun shouldCalculateSafeReportsWithoutDampeningFromInputFile() {
        val reports: List<Report> = readReportsFromFile(inputFilePath)

        val safeReports = reports.filter { it.isBasicSafe() }
        assertThat(safeReports).hasSize(252)
    }

    @Test
    fun shouldCalculateSafeReportsWithDampening() {
        val reports: List<Report> = readReportsFromFile(testInputFilePath)
        val safeReports = reports.filter { it.isSafeWithDampenedPatch() }
        assertThat(safeReports).hasSize(4)
    }

    @Test
    fun shouldCalculateSafeReportsWithDampeningFromInputFile() {
        val reports: List<Report> = readReportsFromFile(inputFilePath)

        val safeReports = reports.filter { it.isSafeWithDampenedPatch() }
        assertThat(safeReports).hasSize(324)
    }

    private fun readReportsFromFile(filePath: String) = Reports.fromLines(TextFileInput.fromPath(filePath).readLines())
}