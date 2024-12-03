package day2

import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

class DayTwoTest {

    private val testInputFilePath = "src/test/resources/day2/testInput.txt"

    @Test
    fun shouldCalculateSafeReports() {
        val reports: List<Report> = TextInput.fromPath(testInputFilePath)
            .getReportsValues()
            .map { Report(it) }
        val safeReports = reports.filter { it.isBasicSafe() }
        assertThat(safeReports).hasSize(2)
    }

    @Test
    fun shouldCalculateSafeReportsWithDampening() {
        val reports: List<Report> = TextInput.fromPath(testInputFilePath)
            .getReportsValues()
            .map { Report(it) }
        val safeReports = reports.filter { it.isSafeWithDampenedPatch() }
        assertThat(safeReports).hasSize(4)
    }
}