package ru.scratty.db

import com.mongodb.MongoClient
import com.mongodb.MongoClientOptions
import com.mongodb.ServerAddress
import org.bson.codecs.configuration.CodecRegistries
import org.bson.codecs.pojo.PojoCodecProvider
import ru.scratty.Config
import ru.scratty.db.dao.ISummariesDAO
import ru.scratty.db.dao.SummariesDAO
import ru.scratty.db.models.Summaries
import ru.scratty.fromISOLocalDateString
import ru.scratty.toISOLocalDateString
import java.time.LocalDate

class MongoDBService private constructor() {

    private object Holder {
        val INSTANCE = MongoDBService()
    }

    companion object {
        val INSTANCE: MongoDBService by lazy { Holder.INSTANCE }
    }

    private val summariesDAO: ISummariesDAO

    init {
        val pojoCodecRegistry = CodecRegistries.fromRegistries(
            MongoClient.getDefaultCodecRegistry(),
            CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()))

        val client = MongoClient(ServerAddress(Config.DB_HOST, Config.DB_PORT), MongoClientOptions.builder().codecRegistry(pojoCodecRegistry).build())
        val db = client.getDatabase(Config.DB_DATABASE)

        summariesDAO = SummariesDAO(db)
    }

    fun addSummaries(summaries: Summaries) = summariesDAO.insert(summaries)

    fun getSummaries(day: LocalDate) = summariesDAO.findOne(day.toISOLocalDateString())

    fun getSummaries(start: LocalDate, end: LocalDate) = summariesDAO.findAll().filter {
        val date = it.date.fromISOLocalDateString()

        date.isEqual(start) || date.isEqual(end) || date.isAfter(start) && date.isBefore(end)
    }

    fun getAllSummaries() = summariesDAO.findAll()
}