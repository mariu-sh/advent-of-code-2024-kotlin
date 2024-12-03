package org.example.day3

import java.io.FileInputStream

class Input(
    private val input: FileInputStream
) {
    companion object {
        fun fromPath(path: String): Input = Input(FileInputStream(path))
    }

    fun read(): String = String(input.readAllBytes()).replace("\n", "")
}