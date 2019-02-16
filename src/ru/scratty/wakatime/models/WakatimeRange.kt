package ru.scratty.wakatime.models

data class WakatimeRange(
    var date: String,
    var start: String,
    var end: String,
    var text: String,
    var timezone: String
)