package ru.scratty

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

            val data = Wakatime(apiKey).parseSummaries(LocalDate.of(2019, 2, 16))
            println(data)
        }
    }
}