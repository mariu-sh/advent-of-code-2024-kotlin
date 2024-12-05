package org.example.day5

data class Update(private val values: List<Int>) {
    fun sortedWith(comparator: Comparator<Int>): Update = Update(values.sortedWith(comparator))

    val middleItem: Int
        get() = values[values.size / 2]

    override fun equals(other: Any?): Boolean {
        return when (other) {
            null -> false
            is Update -> values == other.values
            else -> false
        }
    }

    override fun hashCode(): Int = super.hashCode()
}