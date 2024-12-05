package org.example.day5

class Updates(private val values: List<Update>) {

    private var comparator: Comparator<Int> = Comparator.naturalOrder()

    val ordered by lazy {
        createUpdates(values.filter { it == it.sortedWith(comparator) })
    }
    val notOrdered by lazy {
        createUpdates(values.toMutableList().apply { removeAll(ordered.values) })
    }

    fun withComparator(comparator: Comparator<Int>): Updates {
        this.comparator = comparator
        return this
    }

    fun sumMiddleItems() = values.sumOf { it.middleItem }

    fun sort() = createUpdates(values.map { it.sortedWith(comparator) })

    private fun createUpdates(values: List<Update>) = Updates(values).withComparator(comparator)
}