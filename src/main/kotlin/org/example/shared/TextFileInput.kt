package org.example.shared

import java.io.FileInputStream

class TextFileInput(
    private val input: FileInputStream
) {
    companion object {
        fun fromPath(path: String): TextFileInput = TextFileInput(FileInputStream(path))
    }

    fun read(): String = String(input.readAllBytes()).replace("\n", "")
    fun readLines(): List<String> = input.bufferedReader().readLines()
}