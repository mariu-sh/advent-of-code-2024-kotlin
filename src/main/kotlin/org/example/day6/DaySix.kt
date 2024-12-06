package org.example.day6

import org.example.shared.TextFileInput

class DaySix(private val inputPath: String = "src/main/resources/day6/input.txt") {

    fun solve(): Pair<Int, Int> {
        val areaMap = AreaMap.fromInput(inputPath)
        val guard = areaMap.findGuard()

        while(areaMap.isInBounds(guard.nextPosition())) {
            if (areaMap.isPositionObstacle(guard.nextPosition())) {
                guard.rotate()
            } else {
                areaMap.mark(guard.currentPosition())
                guard.move()
            }
        }

        val markedCells = areaMap.countMarkedCells() + 1 // +1 for the last position in area

        return Pair(markedCells, 0)
    }

}

class AreaMap(private val area: List<CharArray>) {
    private val OBSTACLE = '#'
    private val MARK = 'X'

    companion object {
        fun fromInput(filePath: String) = fromInput(TextFileInput.fromPath(filePath).readLines())
        private fun fromInput(input: List<String>) = AreaMap(input.map { it.toCharArray() })
    }

    fun countMarkedCells(): Int {
        return area.sumOf { it.count { cell -> cell == MARK } }
    }

    fun mark(position: Pair<Int, Int>) {
        area[position.second][position.first] = MARK
    }

    fun isInBounds(position: Pair<Int, Int>): Boolean {
        return position.first in area.indices &&
                position.second in area[position.first].indices
    }

    fun isPositionObstacle(position: Pair<Int, Int>): Boolean {
        return area[position.second][position.first] == OBSTACLE
    }

    fun findGuard() : Guard {
        area.mapIndexed { y, y_line ->
            y_line.mapIndexed { x, cell ->
                if (cell in GuardDirections.indicators) return Guard(Pair(x, y), GuardDirections.fromIndicator(cell))
            }
        }
        throw IllegalArgumentException("No guard found")
    }
}

class Guard(
    private var position: Pair<Int, Int>,
    private var direction: GuardDirections,
) {
    fun move() {
        position += direction.nextStepVector
    }

    fun currentPosition(): Pair<Int, Int> {
        return position
    }

    fun nextPosition(): Pair<Int, Int> {
        return position + direction.nextStepVector
    }

    fun rotate() {
        direction = direction.nextDirection()
    }

    private operator fun Pair<Int, Int>.plus(other: Pair<Int, Int>): Pair<Int, Int> {
        return Pair(this.first + other.first, this.second + other.second)
    }
}

enum class GuardDirections(
    val indicator: Char,
    val order: Int = 0,
    val nextStepVector: Pair<Int, Int>
){
    UP('^', 0, Pair(0, -1)),
    RIGHT('>', 1, Pair(1, 0)),
    DOWN('v', 2, Pair(0, 1)),
    LEFT('<', 3, Pair(-1, 0));

    companion object {
        fun fromIndicator(indicator: Char): GuardDirections {
            return entries.first { it.indicator == indicator.lowercaseChar() }
        }
        val indicators: List<Char>
            get() = entries.map { it.indicator }
    }

    fun nextDirection(): GuardDirections {
        return entries[(order + 1) % entries.size]
    }
}