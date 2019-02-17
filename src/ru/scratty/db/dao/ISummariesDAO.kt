package ru.scratty.db.dao

import ru.scratty.db.models.Summaries

interface ISummariesDAO {

    fun findOne(date: String): Summaries
    fun findAll(): List<Summaries>
    fun insert(summaries: Summaries): String
    fun update(summaries: Summaries)
    fun delete(date: String)
}