package org.example.day8

class DayEight(private val inputFilePath: String = "src/main/resources/day8/input.txt") {

    fun solve(): Pair<Int, Int> {

        val grid = Grid.fromInput(inputFilePath)

        return grid.countBasicAntinodes() to grid.countHarmonicAntinodes()
    }

}