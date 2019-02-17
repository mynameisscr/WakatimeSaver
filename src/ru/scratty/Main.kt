package ru.scratty

import ru.scratty.db.MongoDBService
import ru.scratty.db.models.Summaries
import ru.scratty.wakatime.Wakatime
import java.time.LocalDate

class Main {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val apiKey: String

            if (args.isEmpty()) {
                apiKey = ApiKeyHelper.readApiKey()
            } else {
                apiKey = args[0]
                ApiKeyHelper.saveApiKey(apiKey)
            }

            if (apiKey.isEmpty()) {
                println("Api key not found")
                return
            }

            val data = Wakatime(apiKey).parseSummaries(LocalDate.of(2019, 2, 17))
            println(data)
            println()
            data.listSummaries.forEach {
                println(Summaries.create(it))
            }

            val dbService = MongoDBService.INSTANCE

            data.listSummaries.forEach {
                println(dbService.addSummaries(Summaries.create(it)))
            }

            println()
            dbService.getSummaries(LocalDate.of(2019, 2, 16), LocalDate.of(2019, 2, 17)).forEach {
                println(it)
            }
        }
    }
}