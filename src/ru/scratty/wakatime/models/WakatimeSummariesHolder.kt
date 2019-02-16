package ru.scratty.wakatime.models

import com.beust.klaxon.Json

data class WakatimeSummariesHolder(
    @Json(name = "data")
    var listSummaries: List<WakatimeSummaries>,
    var start: String,
    var end: String
)