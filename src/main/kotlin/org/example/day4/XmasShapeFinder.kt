package org.example.day4

/**
 * 1. Find all A letters considering:
 *  - 'A' is always center of an "X"
 *  - 'A' on the border of matrix is then never part of X-MAS shape (limit ranges)
 * 2. For each 'A' check only diagonal corners.
 * 3. Pair values of diagonal corners
 * 4. Those pairs have to met following conditions:
 *  - They have to be either ('M','S') or ('S','M')
 *  - BOTH diagonals have to meet this requirement for full "X" shape
 */
class XmasShapeFinder(private val matrix: List<CharArray>) {
    companion object {
        val VALID_DIAGONALS = arrayOf(Pair('M', 'S'), Pair('S', 'M'))
    }

    fun count(): Int = IntRange(1, matrix[0].size - 2).sumOf { charIndex ->
        getXmasShapeCountForColumn(charIndex)
    }

    private fun getXmasShapeCountForColumn(charIndex: Int) =
        IntRange(1, matrix.size - 2).count { rowIndex ->
            isXmasShapeCenteredOnCoordinates(charIndex, rowIndex)
        }

    private fun isXmasShapeCenteredOnCoordinates(charIndex: Int, rowIndex: Int): Boolean =
        matrix[rowIndex][charIndex] == 'A' &&
                validateDiagonals(rowIndex, charIndex)

    private fun validateDiagonals(rowIndex: Int, charIndex: Int) =
        areDiagonalsValid(getDiagonalsForCoordinates(rowIndex, charIndex))

    private fun areDiagonalsValid(diagonals: Array<Pair<Char, Char>>): Boolean {
        return diagonals.all { it in VALID_DIAGONALS }
    }

    private fun getDiagonalsForCoordinates(
        rowIndex: Int,
        charIndex: Int
    ): Array<Pair<Char, Char>> {
        val topLeft = matrix[rowIndex - 1][charIndex - 1]
        val bottomRight = matrix[rowIndex + 1][charIndex + 1]
        val bottomLeft = matrix[rowIndex + 1][charIndex - 1]
        val topRight = matrix[rowIndex - 1][charIndex + 1]

        return arrayOf(Pair(topLeft, bottomRight), Pair(bottomLeft, topRight))
    }
}