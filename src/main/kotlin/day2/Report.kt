package day2

import kotlin.math.abs

class Report(
    private val values: List<Int>
) {
    companion object {
        const val MAX_SAFE_ABS_VALUE_CHANGE_THRESHOLD = 3
        const val MIN_SAFE_ABS_VALUE_CHANGE_THRESHOLD = 1
    }

    fun isBasicSafe(): Boolean {
        return isSafeForAllValues(values)
    }

    fun isSafeWithDampenedPatch(): Boolean {
        if (isSafeForAllValues(values)) return true
        return isSafeForDampenedValues()
    }

    private fun isSafeForDampenedValues(): Boolean {
        return sequencePotentialDampenedValues(values).any { isSafeForAllValues(it) }
    }

    private fun sequencePotentialDampenedValues(inputList: List<Int>): Sequence<List<Int>> {
        return sequence { inputList.indices.forEach { index -> yield(inputList.filterIndexed { i, _ -> i != index }) } }
    }

    private fun isSafeForAllValues(inputValues: List<Int>) : Boolean {
        val valuesDiffs: List<Int> = inputValues.mapIndexed { index, value ->
            if (index == 0) value else value - inputValues[index - 1]
        }.drop(1)

        val diffsWithinRange = valuesDiffs.map { abs(it) }
            .map { it in MIN_SAFE_ABS_VALUE_CHANGE_THRESHOLD..MAX_SAFE_ABS_VALUE_CHANGE_THRESHOLD }
            .all { it }

        val valuesAscending = valuesDiffs.all { it < 0 }
        val valuesDescending = valuesDiffs.all { it > 0 }

        return diffsWithinRange && (valuesAscending || valuesDescending)
    }
}