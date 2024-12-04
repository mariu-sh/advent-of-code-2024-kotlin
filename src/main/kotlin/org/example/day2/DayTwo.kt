package org.example.day2

import org.example.shared.TextFileInput

class DayTwo {

    private val inputPath = "src/main/resources/day2/input.txt"

    fun solve(){
        val reports: List<Report> = Report.Reports.fromLines(TextFileInput.fromPath(inputPath).readLines())
        val safeReports = reports.filter { it.isBasicSafe() }
        println("Safe reports: ${safeReports.size}")
        val safeReportsWithDampening = reports.filter { it.isSafeWithDampenedPatch() }
        println("Safe reports with dampening: ${safeReportsWithDampening.size}")
    }

}