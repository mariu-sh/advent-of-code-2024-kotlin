package org.example.day5

class DayFive(private val inputFilePath: String = "src/main/resources/day5/input.txt") {
    fun solve(): Pair<Int, Int> {

        val updates = UpdatesParser.parseFromPath(inputFilePath)

        return updates.ordered.sumMiddleItems() to
                updates.notOrdered.sort().sumMiddleItems()
    }
}