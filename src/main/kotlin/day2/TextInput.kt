package day2

import java.nio.file.Files
import java.nio.file.Paths

class TextInput(
    private val lines: List<String>
) {
    companion object {
        fun fromPath(path: String): TextInput = TextInput(Files.readAllLines(Paths.get(path)))
    }

    fun getReportsValues(): List<List<Int>> = lines.map { line ->
        line.removeSuffix("\n").split(" ").map { it.toInt() }
    }
}