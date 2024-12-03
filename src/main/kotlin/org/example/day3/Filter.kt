package org.example.day3

import org.example.day3.Patterns.*

class Filter(private val input: String) {
    companion object {
        fun from(path: String): Filter = Filter(Input.fromPath(path).read())
    }

    private val switch = InclusionSwitch()

    private val combinedRegex by lazy {
        Regex("${MUL.pattern}|${DO.pattern}|${DONT.pattern}")
    }

    fun filterAllMulValues(): Sequence<Pair<Int, Int>> {
        return MUL.regex.findAll(input)
            .map(::parseValuesFromMulMatch)
    }

    private fun parseValuesFromMulMatch(mulMatch: MatchResult): Pair<Int, Int> {
        return mulMatch.value.removePrefix("mul(")
            .removeSuffix(")")
            .split(",")
            .let { it[0].toInt() to it[1].toInt() }
    }

    fun filterLimitedMulValues(): Sequence<Pair<Int, Int>> {
        return combinedRegex.findAll(input)
            .sortedBy { it.range.first }
            .filter(::shouldIncludeMatch)
            .map(::parseValuesFromMulMatch)
    }

    private fun shouldIncludeMatch(match: MatchResult) =
        when (Patterns.fromMatchResult(match)) {
            DONT -> switch.disable().let { false }
            DO -> switch.enable().let { false }
            MUL -> switch.shouldInclude
        }

}