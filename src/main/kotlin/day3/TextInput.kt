package org.example.day3

import java.io.FileInputStream

class TextInput(
    private val input: FileInputStream
) {
    companion object {
        fun fromPath(path: String): TextInput = TextInput(FileInputStream(path))
    }

    fun read(): String = String(input.readAllBytes()).replace("\n", "")
}