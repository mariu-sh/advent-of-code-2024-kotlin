package org.example.day1

import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import java.io.InputStream
import java.nio.file.Path
import java.nio.file.Paths

class Input(
    val group1: List<Int> = emptyList(),
    val group2: List<Int> = emptyList()
) {
    companion object {
        fun from(filePath: String): Input = read(Paths.get(filePath))

        private fun read(path: Path): Input {
            var input = Input()
            csvReader().open(readInputFile(path)) {
                readAllAsSequence().forEach { row ->
                    val singleRow = parseRow(row)
                    input = input.appendSingleRow(singleRow)
                }
            }
            return input
        }

        private fun parseRow(row: List<String>): Pair<Int, Int> {
            val parsedValues = row.first().split("   ")
            return Pair(
                parsedValues[0].toInt(),
                parsedValues[1].toInt()
            )
        }

        private fun readInputFile(path: Path): InputStream {
            return path.toFile().inputStream()
        }
    }

    private fun appendSingleRow(singleRow: Pair<Int, Int>): Input {
        return Input(
            group1 + singleRow.first,
            group2 + singleRow.second
        )
    }
}