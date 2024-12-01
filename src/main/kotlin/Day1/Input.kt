package Day1

import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import java.io.InputStream
import java.nio.file.Paths

class Input(
    val group1: List<Int> = emptyList(),
    val group2: List<Int> = emptyList()
) {
    private val path = Paths.get("src/main/resources/Day1/input.csv")

    private fun appendSingleRow(singleRow: SingleRow): Input {
        return Input(
            group1 + singleRow.number1,
            group2 + singleRow.number2
        )
    }

    private fun readInputFile(): InputStream {
        return path.toFile().inputStream()
    }

    fun read(): Input {
        var input = Input()
        csvReader().open(readInputFile()) {
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

    data class SingleRow(val number1: Int, val number2: Int)
}