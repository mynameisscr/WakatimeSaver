package ru.scratty.db.models

import org.bson.codecs.pojo.annotations.BsonId
import ru.scratty.wakatime.models.WakatimeSummaries

data class Summaries(
    @BsonId var date: String = "",
    var projects: List<BaseData> = emptyList(),
    var languages: List<BaseData> = emptyList(),
    var editors: List<BaseData> = emptyList(),
    var operatingSystems: List<BaseData> = emptyList(),
    var categories: List<BaseData> = emptyList(),
    var dependencies: List<BaseData> = emptyList()
) {
    companion object {
        fun create(summaries: WakatimeSummaries): Summaries {
            return Summaries(summaries.range.date).apply {
                projects = summaries.projects.map { BaseData.create(it) }
                languages = summaries.languages.map { BaseData.create(it) }
                editors = summaries.editors.map { BaseData.create(it) }
                operatingSystems = summaries.operatingSystems.map { BaseData.create(it) }
                categories = summaries.categories.map { BaseData.create(it) }
                dependencies = summaries.dependencies.map { BaseData.create(it) }
            }
        }
    }
}