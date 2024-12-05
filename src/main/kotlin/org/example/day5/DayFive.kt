package org.example.day5

import org.example.shared.TextFileInput

class DayFive(val inputFilePath: String = "src/main/resources/day5/input.txt") {
    fun solvePartOne(): Pair<Int, Int> {
        val orderDefinitions = OrderDefinitions()
        val updates = Updates()
        TextFileInput.fromPath(inputFilePath).readLines().forEach { line ->
            when {
                line.contains("|") -> orderDefinitions.appendDefinition(line)
                line.isBlank() -> {} //ignore
                else -> updates.appendUpdate(line)
            }
        }
        println()

        val comparator = OrderComparator.fromDefinitionsMap(orderDefinitions.getDefinitionsMap())

        val validUpdates = updates.updates.filter { update ->
            val sortedUpdate = update.sortedWith(comparator)
            update == sortedUpdate
        }

        val notValidUpdates = updates.updates.toMutableList()
            .map { it.sortedWith(comparator) }
            .toMutableList()
            .apply { removeAll(validUpdates) }

        return validUpdates.sumOf { it[it.size / 2] } to notValidUpdates.sumOf { it[it.size/2] }
    }
}

class OrderDefinitions(private val definitions: MutableList<Pair<Int, Int>> = mutableListOf()) {
    fun appendDefinition(definitionString: String) {
        val parsedDefinition = definitionString.split("|")
        appendDefinition(parsedDefinition[0].toInt() to parsedDefinition[1].toInt())
    }

    private fun appendDefinition(definition: Pair<Int, Int>) {
        definitions.add(definition)
    }

    fun getDefinitionsMap(): Map<Int, List<Int>> {
        return definitions.groupBy({ it.first }, { it.second })
    }
}

class Updates {

    private val _updates: MutableList<List<Int>> = mutableListOf()
    val updates: List<List<Int>>
        get() = _updates.toList()

    fun appendUpdate(update: String) {
        _updates.add(update.split(",").map { it.toInt() })
    }
}

class OrderComparator(private val definitionsMap: Map<Int, List<Int>>) : Comparator<Int> {

    companion object {
        fun fromDefinitionsMap(definitionsMap: Map<Int, List<Int>>) = OrderComparator(definitionsMap)
    }

    override fun compare(o1: Int?, o2: Int?): Int {
        if (o1 == null || o2 == null) return 0
        val o1List = definitionsMap.getOrDefault(o1, emptyList())
        if (o1List.isEmpty()) return 0
        if (o2 in o1List) return -1
        return (-1)*compare(o2, o1)
    }
}