package org.example.day5

import java.io.File

object UpdatesParser {

    fun parseFromPath(filePath: String): Updates {
        return parseSections(filePath).toUpdates()
    }

    private fun List<String>.toUpdates() = parseUpdates(this[1])
        .withComparator(orderComparator(parseDefinitions(this[0])))

    private fun orderComparator(definitions: OrderDefinitions) =
        OrderComparator.fromDefinitions(definitions)

    private fun parseSections(filePath: String): List<String> {
        val fileContent = File(filePath).readText()
        val sections = fileContent.split(Regex("\\R\\s*\\R"))
        if (sections.size != 2) {
            throw IllegalArgumentException("The file contains 2 or more sections.")
        }
        return sections
    }

    private fun parseUpdates(section: String): Updates =
        Updates(section.lines().map { it.trim() }
            .map { parseUpdate(it) })

    private fun parseUpdate(values: String): Update {
        return Update(values.split(",").map { it.toInt() })
    }

    private fun parseDefinitions(section: String) =
        OrderDefinitions(section.lines().map { it.trim() }
            .map { it.split("|") }
            .map { it[0].toInt() to it[1].toInt() }
            .groupBy({ it.first }, { it.second }))
}