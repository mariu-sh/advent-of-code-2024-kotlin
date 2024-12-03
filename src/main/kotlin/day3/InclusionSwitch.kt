package day3

class InclusionSwitch {
    var shouldInclude = true
        private set

    fun enable() {
        shouldInclude = true
    }

    fun disable() {
        shouldInclude = false
    }
}