package org.example.day4

import org.example.day3.Input
import org.example.day4.WordFinder.MatrixHandler.rotate
import org.example.day4.WordFinder.MatrixHandler.shiftLeft
import org.example.day4.WordFinder.MatrixHandler.shiftRight

class DayFour(private val inputPath: String = "src/main/resources/day4/input.txt") {
    private val wordToFind: Array<Char> = arrayOf('X', 'M', 'A', 'S')
    fun solvePartOne(): Int {
        var totalCounter = 0
        //1. Find word in lines
        //2. Find word in lines reversed
        //3. Rotate Matrix
        //3. Find word in lines (which are columns)
        //4. Find word in columns (which are columns)
        //

        val matrix = Input.fromPath(inputPath).readlines().map { it.toCharArray() }
        val finder = WordFinder(wordToFind)

        totalCounter += finder.calculateHorizontalOccurrencesInMatrix(matrix)
        totalCounter += finder.calculateVerticalOccurrencesInMatrix(matrix)
        totalCounter += finder.calculateLeftRightDiagonalOccurrencesInMatrix(matrix)
        totalCounter += finder.calculateRightLeftDiagonalOccurrencesInMatrix(matrix)

        return totalCounter
    }

    fun solvePartTwo(): Int {
        //1. Find all A letters starting in range rows: 1..matrix.size-1 chars: 1..row.size
        //2. For each letter A check diagonals (one diagonal, and other) -> Pairs
        //3. Pair should be either M-S or S-M both!!
        var counter = 0
        val validPairs = arrayOf(Pair('M', 'S'), Pair('S', 'M'))
        val matrix = Input.fromPath(inputPath).readlines().map { it.toCharArray() }
        for(charIndex in 1..matrix[0].size-2){
            for(rowIndex in 1..matrix.size-2){
                if(matrix[rowIndex][charIndex] == 'A'){
                    val diagPair1 = Pair(matrix[rowIndex-1][charIndex-1], matrix[rowIndex+1][charIndex+1])
                    val diagPair2 = Pair(matrix[rowIndex+1][charIndex-1], matrix[rowIndex-1][charIndex+1])
                    if(diagPair1 in validPairs && diagPair2 in validPairs){
                        counter++
                    }
                }
            }
        }
        return counter
    }
}

class WordFinder(private val wordToFind: Array<Char> = arrayOf('X', 'M', 'A', 'S')) {

    fun calculateHorizontalOccurrencesInMatrix(matrix: List<CharArray>): Int {
        var counter = 0
        for (row in matrix) {
            val regex = Regex(wordToFind.joinToString(""))
            counter += regex.findAll(row.joinToString("")).toList().size
            counter += regex.findAll(row.reversed().joinToString("")).toList().size
        }
        return counter
    }

    fun calculateVerticalOccurrencesInMatrix(matrix: List<CharArray>): Int {
        val rotatedMatrix = matrix.rotate()
        return calculateHorizontalOccurrencesInMatrix(rotatedMatrix)
    }

    fun calculateLeftRightDiagonalOccurrencesInMatrix(matrix: List<CharArray>): Int {
        val shiftedMatrix = matrix.shiftRight().rotate()
        return calculateHorizontalOccurrencesInMatrix(shiftedMatrix)
    }

    fun calculateRightLeftDiagonalOccurrencesInMatrix(matrix: List<CharArray>): Int {
        val shiftedMatrix = matrix.shiftLeft().rotate()
        return calculateHorizontalOccurrencesInMatrix(shiftedMatrix)

    }

    object MatrixHandler {

        fun List<CharArray>.rotate(): List<CharArray> = rotateMatrix(this)
        fun List<CharArray>.shiftLeft(): List<CharArray> = shiftMatrixLeft(this)
        fun List<CharArray>.shiftRight(): List<CharArray> = shiftMatrixRight(this)

        private fun rotateMatrix(matrix: List<CharArray>): List<CharArray> {
            val rotatedMatrix = mutableListOf<CharArray>()
            for (charIndex in matrix[0].indices) {
                val row = CharArray(matrix.size)
                for (rowIndex in matrix.indices) {
                    row[rowIndex] = matrix[rowIndex][charIndex]
                }
                rotatedMatrix.add(row)
            }
            return rotatedMatrix.toList()
        }

        private fun shiftMatrixLeft(matrix: List<CharArray>): List<CharArray> {
            val placeholder = 'O'
            val maxShift = matrix.size - 1
            val newLength = matrix[0].size + maxShift
            val shiftedMatrix = mutableListOf<String>()
            for (rowIndex in matrix.indices) {
                val row = CharArray(newLength) { placeholder }
                for (charIndex in matrix[rowIndex].indices) {
                    row[charIndex + rowIndex] = matrix[rowIndex][charIndex]
                }
                shiftedMatrix.add(row.joinToString(""))
            }
            return shiftedMatrix.map { it.toCharArray() }.toList()
        }

        private fun shiftMatrixRight(matrix: List<CharArray>): List<CharArray> {
            val placeholder = 'O'
            val maxShift = matrix.size - 1
            val newLength = matrix[0].size + maxShift
            val shiftedMatrix = mutableListOf<String>()
            for (rowIndex in matrix.indices) {
                val row = CharArray(newLength) { placeholder }
                for (charIndex in matrix[rowIndex].indices) {
                    row[charIndex + maxShift - rowIndex] = matrix[rowIndex][charIndex]
                }
                shiftedMatrix.add(row.joinToString(""))
            }
            return shiftedMatrix.map { it.toCharArray() }.toList()
        }
    }
}
