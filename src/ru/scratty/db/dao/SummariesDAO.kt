package ru.scratty.db.dao

import com.mongodb.client.MongoDatabase
import com.mongodb.client.model.Filters.eq
import com.mongodb.client.model.UpdateOptions
import com.mongodb.client.model.Updates.combine
import com.mongodb.client.model.Updates.set
import ru.scratty.db.models.Summaries

class SummariesDAO(db: MongoDatabase): ISummariesDAO {

    companion object {
        private const val COLLECTION_NAME = "summaries"
    }

    private val collection = db.getCollection(COLLECTION_NAME, Summaries::class.java)

    override fun findOne(date: String): Summaries {
        val iter = collection.find(eq("date", date))
        return iter.first() ?: Summaries()
    }

    override fun findAll() = collection.find().toList()

    override fun insert(summaries: Summaries): String {
        update(summaries, true)
        return summaries.date
    }

    override fun update(summaries: Summaries) {
        update(summaries, false)
    }

    override fun delete(date: String) {
        collection.deleteOne(eq("date", date))
    }

    private fun update(obj: Summaries, upsert: Boolean = false) {
        val updateFields = combine(
            set("projects", obj.projects),
            set("languages", obj.languages),
            set("editors", obj.editors),
            set("operating_systems", obj.operatingSystems),
            set("categories", obj.categories),
            set("dependencies", obj.dependencies)
        )
        collection.updateOne(eq("date", obj.date), updateFields, UpdateOptions().upsert(upsert))
    }
}