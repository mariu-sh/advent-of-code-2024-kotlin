package day3

class IncludingEnabler {
    var shouldInclude = true
        private set

    fun enable() {
        shouldInclude = true
    }

    fun disable() {
        shouldInclude = false
    }
}