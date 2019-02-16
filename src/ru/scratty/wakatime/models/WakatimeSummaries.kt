package ru.scratty.wakatime.models

import com.beust.klaxon.Json

data class WakatimeSummaries(
    @Json(name = "grand_total") var grandTotal: WakatimeGrandTotal,
    var projects: List<WakatimeData>,
    var languages: List<WakatimeData>,
    var editors: List<WakatimeData>,
    @Json(name = "operating_systems") var operatingSystems: List<WakatimeData>,
    var categories: List<WakatimeData>,
    var dependencies: List<WakatimeData>,
    var range: WakatimeRange
)