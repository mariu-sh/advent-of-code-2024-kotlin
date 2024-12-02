package org.example.day2

import java.nio.file.Files
import java.nio.file.Paths
import kotlin.math.abs

class DayTwo {

    private val inputPath = "src/main/resources/day2/input.txt"

    fun partOneSolution(){
        val reports: List<Report> = TextInput.fromPath(inputPath).getReportsValues().map { Report(it) }
        val safeReports = reports.filter { it.isSafe() }
        println("Safe reports: ${safeReports.size}")
    }

    class Report(
        private val values: List<Int>
    ) {
        companion object {
            const val MAX_SAFE_ABS_VALUE_CHANGE_THRESHOLD = 3
            const val MIN_SAFE_ABS_VALUE_CHANGE_THRESHOLD = 1
        }

        fun isSafe(): Boolean {
            val valuesDiffs = values.mapIndexed { index, value ->
                if (index == 0) value else value - values[index - 1]
            }.drop(1)

            val diffsWithinRange = valuesDiffs.map { abs(it) }
                .map { it in MIN_SAFE_ABS_VALUE_CHANGE_THRESHOLD..MAX_SAFE_ABS_VALUE_CHANGE_THRESHOLD }
                .all { it } //after previous mapping, we get list true/false, alle should be true to condition be true

            val allDiffsIncreasing = valuesDiffs.all { it > 0 }
            val allDiffsDecreasing = valuesDiffs.all { it < 0 }

            return diffsWithinRange && (allDiffsIncreasing || allDiffsDecreasing)
        }

    }

    class TextInput(
        private val lines: List<String>
    ) {
        companion object {
            fun fromPath(path: String): TextInput {
                return TextInput(Files.readAllLines(Paths.get(path)))
            }
        }

        fun getReportsValues(): List<List<Int>> {
            return lines.map { line ->
                line.removeSuffix("\n").split(" ")
                    .map { it.toInt() }
            }
        }
    }
}