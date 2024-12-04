package org.example.day4

object MatrixHandler {

    fun List<CharArray>.rotate(): List<CharArray> = rotateMatrix(this)
    fun List<CharArray>.shiftLeft(): List<CharArray> = shiftMatrixLeft(this)
    fun List<CharArray>.shiftRight(): List<CharArray> = shiftMatrixRight(this)

    private fun rotateMatrix(matrix: List<CharArray>): List<CharArray> {
        return matrix[0].indices.map { columnIndex -> extractColumnAsRow(matrix, columnIndex) }
    }

    private fun extractColumnAsRow(matrix: List<CharArray>, columnIndex: Int): CharArray {
        val numberOfRows = matrix.size
        return CharArray(numberOfRows) { matrixRowIndex -> matrix[matrixRowIndex][columnIndex] }
    }

    /**
     * This method modifies matrix shifting each row in fact moving letter's index by vector [+(rowIndex+1)]
     * XM  -> XMoo
     * AS  -> oASo
     * XS  -> ooXS
     */
    private fun shiftMatrixLeft(matrix: List<CharArray>): List<CharArray> {
        val placeholder = 'o'
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

    /**
     * This method modifies matrix shifting each row in fact moving letter's index by vector [-(rowIndex+1)]
     * XM  -> ooXM
     * AS  -> oASo
     * XS  -> XSoo
     */
    private fun shiftMatrixRight(matrix: List<CharArray>): List<CharArray> {
        val placeholder = 'o'
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