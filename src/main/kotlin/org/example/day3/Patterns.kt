package org.example.day3

enum class Patterns(
    val pattern: String
) {
    MUL("""mul\(\d{1,3},\d{1,3}\)"""),
    DO("""do\(\)"""),
    DONT("""don't\(\)""");

    val regex by lazy { Regex(pattern) }

    companion object {
        fun fromMatchResult(matchResult: MatchResult): Patterns {
            return entries.first { it.regex.matches(matchResult.value) }
        }
    }
}