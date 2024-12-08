package org.example.day8

import org.example.shared.TextFileInput

class DayEight(private val inputFilePath: String = "src/main/resources/day8/input.txt") {

    fun solve(): Pair<Int, Int> {

        val grid = Grid.fromInput(inputFilePath)

        println(grid.antennasLocationsMap)
        return grid.countBasicAntinodes() to grid.countHarmonicAntinodes()
    }

}

class Grid(private val _grid: List<CharArray>) {

    companion object {
        fun fromInput(inputFilePath: String) = Grid(
            TextFileInput.fromPath(inputFilePath).readLines().map { line -> line.toCharArray() }
        )
    }

    val antennasLocationsMap by lazy { getAntennasCoordinatesMap() }
    private val _basicAntinodesLocations: MutableSet<Pair<Int, Int>> = mutableSetOf() //not duplicated locations
    private val _harmonicAntinodesLocations: MutableSet<Pair<Int, Int>> = mutableSetOf()

    fun countBasicAntinodes(): Int {
        findBasicAntinodesLocations()
        return _basicAntinodesLocations.size
    }

    fun countHarmonicAntinodes(): Int {
        findHarmonicAntinodesLocations()
        return _harmonicAntinodesLocations.size
    }

    private fun findHarmonicAntinodesLocations() {
        antennasLocationsMap.forEach { (_, locations) ->
            findHarmonicsForLocations(locations)
        }
    }

    private fun findHarmonicsForLocations(locations: List<Pair<Int, Int>>) {
        _harmonicAntinodesLocations.add(locations.first())
        val vectors = getVectorsForLocations(locations)
        locations.forEach { location -> findHarmonicsForLocation(location, vectors[location]!!) }
    }

    private fun getVectorsForLocations(locations: List<Pair<Int, Int>>): Map<Pair<Int, Int>, Set<GridVector>> {
        with(locations.toMutableSet()) {
            return locations.associateWith { location ->
                this.remove(location)
                val vectors = this.map { GridVector.fromLocations(location, it) }.toSet()
                vectors + vectors.map { it.negative() }
            }
        }
    }


    private fun findHarmonicsForLocation(initLocation: Pair<Int, Int>, vectors: Set<GridVector>) {
        vectors.forEach { vector ->
            var newLocation = vector.newLocation(initLocation)
            while (isInBounds(newLocation)) {
                _harmonicAntinodesLocations.add(newLocation)
                newLocation = vector.newLocation(newLocation)
            }
        }
    }

    private fun findBasicAntinodesLocations() {
        antennasLocationsMap.forEach { (_, locations) ->
            locations.forEach { antennaLocation ->
                locations.filter { it != antennaLocation }
                    .map { GridVector.fromLocations(antennaLocation, it).doubled() }
                    .map { it.newLocation(antennaLocation) }
                    .filter { isInBounds(it) }
                    .forEach { _basicAntinodesLocations.add(it) }
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

    operator fun plus(other: GridVector) = GridVector(x + other.x, y + other.y)

    fun doubled() = GridVector(x * 2, y * 2)
    fun negative() = GridVector(-x, -y)
    fun newLocation(initLocation: Pair<Int, Int>) = initLocation.first + x to initLocation.second + y
}
