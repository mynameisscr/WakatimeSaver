package ru.scratty.saver

data class Period(
    var hours: Int = 0,
    var minutes: Int = 0,
    var seconds: Int = 0
) {
    fun milliseconds() = (hours * 60 * 60 + minutes * 60 + seconds) * 1000L
}