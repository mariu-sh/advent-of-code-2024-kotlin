package org.example.day7

object Permutator{
    fun <T> permutedList(list: List<T>, depth: Int): List<List<T>> {
        if (list.size == 1) return listOf(list)
        if (depth == 1) return list.map { listOf(it) }

        val shallowerPermute = permutedList(list, depth - 1)
        return shallowerPermute.flatMap { permutations -> list.map { permutations + it } }
    }
}