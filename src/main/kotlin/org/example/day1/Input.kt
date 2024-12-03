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
                    val splitRow = row.first().split("   ")
                    input = input.appendSingleRow(
                        SingleRow(
                            splitRow[0].toInt(),
                            splitRow[1].toInt()
                        )
                    )
                }
            }
            return input
        }

        private fun readInputFile(path: Path): InputStream {
            return path.toFile().inputStream()
        }
    }

    private fun appendSingleRow(singleRow: SingleRow): Input {
        return Input(
            group1 + singleRow.number1,
            group2 + singleRow.number2
        )
    }

    data class SingleRow(val number1: Int, val number2: Int)
}