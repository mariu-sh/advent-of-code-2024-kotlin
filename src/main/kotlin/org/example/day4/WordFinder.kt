package org.example.day4

import org.example.day4.MatrixHandler.rotate
import org.example.day4.MatrixHandler.shiftLeft
import org.example.day4.MatrixHandler.shiftRight

class WordFinder(private val wordToFind: Array<Char> = arrayOf('X', 'M', 'A', 'S')) {

    fun calculateAllOccurrences(matrix: List<CharArray>): Int{
        return calculateHorizontalOccurrencesInMatrix(matrix) +
                calculateVerticalOccurrencesInMatrix(matrix) +
                calculateLeftRightDiagonalOccurrencesInMatrix(matrix) +
                calculateRightLeftDiagonalOccurrencesInMatrix(matrix)
    }

    private fun calculateHorizontalOccurrencesInMatrix(matrix: List<CharArray>): Int {
        return matrix.sumOf { row -> countWordMatchesInRow(row) + countWordMatchesInRow(row.reversedArray()) }
    }

    private fun countWordMatchesInRow(row: CharArray) = Regex(wordToFind.joinToString(""))
        .findAll(row.joinToString(""))
        .toList()
        .size

    private fun calculateVerticalOccurrencesInMatrix(matrix: List<CharArray>): Int {
        val rotatedMatrix = matrix.rotate()
        return calculateHorizontalOccurrencesInMatrix(rotatedMatrix)
    }

    private fun calculateLeftRightDiagonalOccurrencesInMatrix(matrix: List<CharArray>): Int {
        val shiftedMatrix = matrix.shiftRight().rotate()
        return calculateHorizontalOccurrencesInMatrix(shiftedMatrix)
    }

    private fun calculateRightLeftDiagonalOccurrencesInMatrix(matrix: List<CharArray>): Int {
        val shiftedMatrix = matrix.shiftLeft().rotate()
        return calculateHorizontalOccurrencesInMatrix(shiftedMatrix)
    }

}