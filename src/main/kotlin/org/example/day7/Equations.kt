package org.example.day7

import org.example.shared.TextFileInput

class Equations(private val _equations: List<Equation>) {
    companion object {
        fun fromInput(inputFilePath: String) = Equations(
            TextFileInput.fromPath(inputFilePath).readLines().map { Equation.fromLine(it) }
        )
    }

    fun sumResultsSolvableWithConcatenation() = solvableEquationsWithConcatenation().sumOf { it.result }
    fun sumResultsSolvableByPlusAndMultiply() = solvableEquations().sumOf { it.result }

    private fun solvableEquationsWithConcatenation() = _equations.filter { it.isSolvableWithPlusMultiplyAndConcatenate() }
    private fun solvableEquations() = _equations.filter { it.isSolvableWithPlusAndMultiply() }
}