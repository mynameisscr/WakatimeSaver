package ru.scratty.wakatime.models

import com.beust.klaxon.Json

data class WakatimeGrandTotal(
    var digital: String,
    var hours: Int,
    var minutes: Int,
    @Json(name = "total_seconds")
    var totalSeconds: Int,
    var text: String
)