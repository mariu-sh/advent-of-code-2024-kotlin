package org.example.day8

import org.example.shared.TextFileInput

class DayEight(private val inputFilePath: String = "src/main/resources/day8/input.txt") {

    fun solve(): Pair<Int, Int> {

        val grid = Grid.fromInput(inputFilePath)

        println(grid.antennasLocations)
        return grid.countAntinodes() to 0
    }

}

class Grid(private val _grid: List<CharArray>) {

    companion object {
        fun fromInput(inputFilePath: String) = Grid(
            TextFileInput.fromPath(inputFilePath).readLines().map { line -> line.toCharArray() }
        )
    }

    val antennasLocations by lazy { getAntennasCoordinatesMap() }
    private val _antinodesLocations: MutableSet<Pair<Int, Int>> = mutableSetOf() //not duplicated locations

    fun countAntinodes(): Int {
        findAntinodes()
        return _antinodesLocations.size
    }

    private fun findAntinodes() {
        antennasLocations.forEach { (_, locations) ->
            locations.forEach { antennaLocation ->
                locations.filter { it != antennaLocation }
                    .map { GridVector.fromLocations(antennaLocation, it).doubled() }
                    .map { it.newLocation(antennaLocation) }
                    .filter { isInBounds(it) }
                    .forEach{ _antinodesLocations.add(it) }
            }
        }
    }

    private fun isInBounds(location: Pair<Int, Int>): Boolean {
        return location.second in _grid.indices
                && location.first in _grid[0].indices
    }

    private fun getAntennasCoordinatesMap() = _grid.mapIndexed { rowIndex, charArray ->
        charArray.mapIndexed { charIndex, char ->
            if (char.isLetterOrDigit()) char to Pair(charIndex, rowIndex) else null
        }.filterNotNull()
    }.flatten()
        .groupBy { it.first }
        .mapValues { it.value.map { it.second } }
}

class GridVector(
    private val x: Int,
    private val y: Int
) {
    companion object {
        fun fromLocations(location1: Pair<Int, Int>, location2: Pair<Int, Int>) =
            GridVector(location2.first - location1.first, location2.second - location1.second)
    }
    fun doubled() = GridVector(x * 2, y * 2)
    fun newLocation(initLocation: Pair<Int, Int>) = initLocation.first + x to initLocation.second + y
}
