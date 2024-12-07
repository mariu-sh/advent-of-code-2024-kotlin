package org.example.day7

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