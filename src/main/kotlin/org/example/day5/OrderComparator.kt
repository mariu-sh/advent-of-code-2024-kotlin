package org.example.day5

class OrderComparator(private val definitionsMap: Map<Int, List<Int>>) : Comparator<Int> {

    companion object {
        fun fromDefinitions(definitions: OrderDefinitions) =
            fromDefinitionsMap(definitions.definitionsMap)

        private fun fromDefinitionsMap(definitionsMap: Map<Int, List<Int>>) =
            OrderComparator(definitionsMap)
    }

    override fun compare(o1: Int?, o2: Int?): Int {
        if (o1 == null || o2 == null) return 0
        val o1List = definitionsMap.getOrDefault(o1, emptyList())
        return when {
            o1List.isEmpty() -> 0
            o2 in o1List -> -1
            else -> (-1) * compare(o2, o1)
        }
    }
}