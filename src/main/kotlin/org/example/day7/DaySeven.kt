package org.example.day7

class DaySeven(private val inputFilePath: String = "src/main/resources/day7/input.txt") {

    fun solve(): Pair<Long, Long> {

        val equations = Equations.fromInput(inputFilePath)
        val partOneResult = equations.sumResultsSolvableByPlusAndMultiply()
        val partTwoResult = equations.sumResultsSolvableWithConcatenation()

        return partOneResult to partTwoResult
    }

}
