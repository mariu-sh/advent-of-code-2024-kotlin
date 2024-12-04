package org.example

import org.example.day1.DayOne
import org.example.day2.DayTwo
import org.example.day3.DayThree
import org.example.day4.DayFour

fun main() {
    println("Day 1 Solutions:")
    DayOne().solve()

    println("Day 2 Solutions:")
    DayTwo().solve()

    println("Day 3 Solutions:")
    DayThree().solve()

    println("Day 4 Solutions:")
    val dayFour = DayFour()
    println("All occurrences of XMAS: ${dayFour.solvePartOne()}")
    println("All occurrences of X-MAS: ${dayFour.solvePartTwo()}")
}

