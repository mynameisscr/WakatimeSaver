package ru.scratty.wakatime.models

import com.beust.klaxon.Json

data class WakatimeData(
    var digital: String,
    var hours: Int,
    var minutes: Int,
    var seconds: Int,
    @Json(name = "total_seconds")
    var totalSeconds: Int,
    var percent: Double,
    var name: String,
    var text: String
)