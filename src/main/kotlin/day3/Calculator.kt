package org.example.day3

class Calculator {
    fun calculate(values: Sequence<Pair<Int, Int>>): Int = values.sumOf { it.first * it.second }
}