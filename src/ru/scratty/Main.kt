package ru.scratty

import ru.scratty.saver.Period
import ru.scratty.saver.WakatimeSaver

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

            val saver = WakatimeSaver(apiKey, Period(minutes = 15))
            saver.start()
        }
    }
}