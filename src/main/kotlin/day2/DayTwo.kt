package org.example.day2

import day2.Report
import day2.TextInput

class DayTwo {

    private val inputPath = "src/main/resources/day2/input.txt"

    fun solve(){
        val reports: List<Report> = TextInput.fromPath(inputPath).getReportsValues().map { Report(it) }
        val safeReports = reports.filter { it.isBasicSafe() }
        println("Safe reports: ${safeReports.size}")
        val safeReportsWithDampening = reports.filter { it.isSafeWithDampenedPatch() }
        println("Safe reports with dampening: ${safeReportsWithDampening.size}")
    }

}