package org.example.day7

import org.example.shared.TextFileInput

class DaySeven(private val inputFilePath: String = "src/main/resources/day7/input.txt") {

    fun solve(): Pair<Long, Long> {

        val partOneResult = Equations.fromInput(inputFilePath).sumSolvableEquationsResults()

        return partOneResult to 0L
    }

    class Equations(private val _equations: List<Equation>) {
        companion object {
            fun fromInput(inputFilePath: String): Equations = Equations(
                TextFileInput.fromPath(inputFilePath).readLines().map { Equation.fromLine(it) }
            )
        }

        fun sumSolvableEquationsResults(): Long = solvableEquations().sumOf { it.result }

        private fun solvableEquations(): List<Equation> = _equations.filter { it.isSolvable() }
    }

    class Equation(val result: Long, val variables: List<Long>) {
        companion object {
            fun fromLine(line: String): Equation = with(line.split(":")) {
                Equation(result = this[0].trim().toLong(), variables = this[1].trim().split(" ").map { it.trim().toLong() })
            }
        }

        val numberOfOperations: Int
            get() = variables.size - 1

        val times: (Long, Long) -> Long = { a, b -> a * b }
        val plus: (Long, Long) -> Long = { a, b -> a + b }

        val operations = listOf(times, plus)

        private fun permutedOperations(operations: List<(Long, Long) -> Long>, depth: Int): List<List<(Long, Long) -> Long>> {
            if (depth == 1) return operations.map { listOf(it) }

            val previousPermutations = permutedOperations(operations, depth - 1)
            return previousPermutations.flatMap { permutations ->
                operations.map { operation ->
                    permutations + operation
                }
            }
        }

        fun isSolvable(): Boolean {
            permutedOperations(operations, numberOfOperations).forEach { operations ->
                if (calculate(operations) == result) return true
            }
            return false
        }
        private fun calculate(operations: List<(Long, Long)->Long>) : Long {
            return variables.reduceIndexed { index, acc, variable ->
                if (index == 0) acc else operations[index - 1](acc, variable)
            }
        }
    }
}
