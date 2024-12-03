package org.example.day3

class DayThree {
    private val inputPath = "src/main/resources/day3/input.txt"

    fun solve() {
        val calculator = Calculator()
        val filter = Filter.from(inputPath)

        println("All multiplications: ${calculator.calculate(filter.filterAllMulValues())}")
        println("Limited multiplications: ${calculator.calculate(filter.filterLimitedMulValues())}")
    }
}