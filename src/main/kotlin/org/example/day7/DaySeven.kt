package org.example.day7

import org.example.shared.TextFileInput

class DaySeven(private val inputFilePath: String = "src/main/resources/day7/input.txt") {

    fun solve(): Pair<Long, Long> {

        val equations = Equations.fromInput(inputFilePath)
        val partOneResult = equations.sumResultsSolvableByPlusAndMultiply()
        val partTwoResult = equations.sumResultsSolvableWithConcatenation()

        return partOneResult to partTwoResult
    }

    class Equations(private val _equations: List<Equation>) {
        companion object {
            fun fromInput(inputFilePath: String): Equations = Equations(
                TextFileInput.fromPath(inputFilePath).readLines().map { Equation.fromLine(it) }
            )
        }

        fun sumResultsSolvableWithConcatenation() = solvableEquationsWithConcatenation().sumOf { it.result }
        fun sumResultsSolvableByPlusAndMultiply() = solvableEquations().sumOf { it.result }

        private fun solvableEquationsWithConcatenation() = _equations.filter { it.isSolvableWithConcatenation() }
        private fun solvableEquations() = _equations.filter { it.isSolvableWithPlusAndMul() }
    }

    class Equation(val result: Long, val variables: List<Long>) {
        companion object {
            fun fromLine(line: String): Equation = with(line.split(":")) {
                Equation(
                    result = this[0].trim().toLong(),
                    variables = this[1].trim().split(" ").map { it.trim().toLong() })
            }
        }

        private val numberOfOperations: Int
            get() = variables.size - 1

        private val times: (Long, Long) -> Long = { a, b -> a * b }
        private val plus: (Long, Long) -> Long = { a, b -> a + b }
        private val concatenate: (Long, Long) -> Long = { a, b -> (a.toString() + b.toString()).toLong() }

        private val operations = listOf(times, plus)
        private val operationsWithConcatenation = listOf(concatenate) + operations

        private fun permutedOperationsPlusAndTimes(depth: Int) = permutedOperations(operations, depth)
        private fun permutedOperationsConcatenation(depth: Int) = permutedOperations(operationsWithConcatenation, depth)

        private fun permutedOperations(
            operations: List<(Long, Long) -> Long>,
            depth: Int
        ): List<List<(Long, Long) -> Long>> {
            if (depth == 1) return operations.map { listOf(it) }

            val previousPermutations = permutedOperations(operations, depth - 1)
            return previousPermutations.flatMap { permutations ->
                operations.map { operation ->
                    permutations + operation
                }
            }
        }

        fun isSolvableWithConcatenation(): Boolean {
            permutedOperationsConcatenation(numberOfOperations).forEach { operations ->
                if (calculate(operations) == result) return true
            }
            return false
        }

        fun isSolvableWithPlusAndMul(): Boolean {
            permutedOperationsPlusAndTimes(numberOfOperations).forEach { operations ->
                if (calculate(operations) == result) return true
            }
            return false
        }

        private fun calculate(operations: List<(Long, Long) -> Long>): Long {
            return variables.reduceIndexed { index, acc, variable ->
                if (index == 0) acc else operations[index - 1](acc, variable)
            }
        }
    }
}
