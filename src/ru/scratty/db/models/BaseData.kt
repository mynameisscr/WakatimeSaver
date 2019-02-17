package ru.scratty.db.models

import ru.scratty.wakatime.models.WakatimeData

data class BaseData(
    var name: String = "",
    var totalSeconds: Int = -1
) {
    companion object {
        fun create(data: WakatimeData) = BaseData(data.name, data.totalSeconds)
    }
}