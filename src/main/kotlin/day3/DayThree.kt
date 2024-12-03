package org.example.day3

class DayThree {
    private val inputPath = "src/main/resources/day3/input.txt"

    fun solve() {
        val calculator = Calculator()
        val inputFilter = InputFilter.from(inputPath)

        println("All multiplications: ${calculator.calculate(inputFilter.filterAllMulValues())}")
        println("Limited multiplications: ${calculator.calculate(inputFilter.filterLimitedMulValues())}")
    }
}