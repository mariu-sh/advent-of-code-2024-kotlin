package org.example.day3

class InputFilter(private val input: String) {
    companion object {
        val MUL_REGEX = Regex("""mul\(\d{1,3},\d{1,3}\)""")

        fun from(path: String): InputFilter = InputFilter(TextInput.fromPath(path).read())
    }

    fun filterAllMulValues(): Sequence<Pair<Int, Int>> {
        return getAllMulFromInput().map { it.value }
            .map { it.removePrefix("mul(").removeSuffix(")").split(",") }
            .map { Pair(it[0].toInt(), it[1].toInt()) }
    }

    private fun getAllMulFromInput(): Sequence<MatchResult> {
        return MUL_REGEX.findAll(input)
    }

    fun filterLimitedMulValues(): Sequence<Pair<Int, Int>> {
        TODO("Not yet implemented")
    }
}