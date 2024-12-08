package org.example.day8

class GridVector(
    private val x: Int,
    private val y: Int
) {
    companion object {
        fun fromLocations(location1: Pair<Int, Int>, location2: Pair<Int, Int>) =
            GridVector(location2.first - location1.first, location2.second - location1.second)
    }

    fun doubled() = GridVector(x * 2, y * 2)
    fun negative() = GridVector(-x, -y)
    fun newLocation(initLocation: Pair<Int, Int>) = initLocation.first + x to initLocation.second + y
}