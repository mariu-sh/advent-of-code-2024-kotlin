package org.example

import org.example.day1.DayOne
import org.example.day2.DayTwo
import org.example.day3.DayThree
import org.example.day4.DayFour
import org.example.day5.DayFive
import org.example.day6.DaySix
import org.example.day7.DaySeven

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

    println("Day 5 Solutions:")
    val dayFiveResult = DayFive().solve()
    println("Sum of middle elements for ordered updates: ${dayFiveResult.first}")
    println("Sum of middle elements for unordered updates (after sorting): ${dayFiveResult.second}")

    println("Day 6 Solutions:")
    val daySixResult = DaySix().solve()
    println("DaySix PartOne: ${daySixResult.first}")
    println("DaySix PartTwo: ${daySixResult.second}")   //TODO: To solve

    println("Day 7 Solutions:")
    val daySevenResults = DaySeven().solve()
    println("DaySeven PartOne: ${daySevenResults.first}")
    println("DaySeven PartTwo: ${daySevenResults.second}")

}

