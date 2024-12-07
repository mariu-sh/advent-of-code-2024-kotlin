package org.example.day7

import org.example.day7.Operation.*
import org.example.day7.Permutator.permutedList

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

    fun isSolvableWithPlusMultiplyAndConcatenate(): Boolean = isSolvableWithOperations(listOf(PLUS, TIMES, CONCATENATE))

    fun isSolvableWithPlusAndMultiply(): Boolean = isSolvableWithOperations(listOf(PLUS, TIMES))
    private fun isSolvableWithOperations(operations: List<Operation>): Boolean {
        permutedOperations(operations, numberOfOperations).forEach { operations ->
            if (calculate(operations) == result) return true
        }
        return false
    }

    private fun permutedOperations(operations: List<Operation>, numberOfOperations: Int) =
        permutedList(operations.map { it.operation }, numberOfOperations)

    private fun calculate(operations: List<(Long, Long) -> Long>): Long {
        return variables.reduceIndexed { index, acc, variable ->
            if (index == 0) acc else operations[index - 1](acc, variable)
        }
    }
}