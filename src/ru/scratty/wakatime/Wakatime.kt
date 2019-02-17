package ru.scratty.wakatime

import com.beust.klaxon.Klaxon
import ru.scratty.http.HttpGet
import ru.scratty.http.HttpGetException
import ru.scratty.http.HttpUrl
import ru.scratty.toISOLocalDateString
import ru.scratty.wakatime.models.WakatimeSummariesHolder
import java.time.LocalDate

class Wakatime(private val apiKey: String) {

    companion object {
        private const val SCHEME = "https"
        private const val HOST = "wakatime.com"
        private const val SUMMARIES_PATH = "/api/v1/users/current/summaries"

        private const val API_KEY_PARAMETER = "api_key"
        private const val START_PARAMETER = "start"
        private const val END_PARAMETER = "end"
    }

    fun parseSummaries(day: LocalDate): WakatimeSummariesHolder = parseSummaries(day, day)

    fun parseSummaries(start: LocalDate, end: LocalDate): WakatimeSummariesHolder {
        val url = HttpUrl().apply {
            scheme = SCHEME
            host = HOST
            path = SUMMARIES_PATH
            parameters = mapOf(
                API_KEY_PARAMETER to apiKey,
                START_PARAMETER to start.toISOLocalDateString(),
                END_PARAMETER to end.toISOLocalDateString())
        }

        val get = HttpGet(url)
        get.trustAllCerts()

        val response = get.execute()

        if (response.code != 200) {
            throw HttpGetException(response.code, response.data)
        }

        return Klaxon().parse<WakatimeSummariesHolder>(response.data)!!
    }
}