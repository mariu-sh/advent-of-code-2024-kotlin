package org.example.day4

import org.example.day3.Input

class DayFour(inputPath: String = "src/main/resources/day4/input.txt") {

    private val wordToFind: Array<Char> = arrayOf('X', 'M', 'A', 'S')
    private val matrix = Input.fromPath(inputPath).readlines().map { it.toCharArray() }

    fun solvePartOne(): Int {
        return WordFinder(wordToFind).calculateAllOccurrences(matrix)
    }

    fun solvePartTwo(): Int {
        return XmasShapeFinder(matrix).count()
    }

}

