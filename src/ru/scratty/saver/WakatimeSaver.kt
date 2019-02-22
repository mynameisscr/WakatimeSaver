package ru.scratty.saver

import ru.scratty.db.MongoDBService
import ru.scratty.db.models.Summaries
import ru.scratty.wakatime.Wakatime
import ru.scratty.wakatime.models.WakatimeSummaries
import ru.scratty.wakatime.models.WakatimeSummariesHolder
import java.time.LocalDate
import java.util.*

class WakatimeSaver(
    apiKey: String,
    private val period: Period
) {

    private val wakatime = Wakatime(apiKey)
    private val dbService = MongoDBService.INSTANCE

    private var timer = Timer()

    private var lastSavedDay = LocalDate.now()

    fun start() {
        timer.cancel()

        timer = Timer()
        timer.schedule(object: TimerTask() {
            override fun run() {
                val summaries = parse()

                summaries.listSummaries.forEach {
                    save(it)
                }
            }
        }, 0, period.milliseconds())
    }

    fun stop() {
        timer.cancel()
    }

    fun saveLastTwoWeeks() {
        val start = LocalDate.now().minusDays(13)
        val end = LocalDate.now()

        wakatime.parseSummaries(start, end).listSummaries.forEach {
            dbService.addSummaries(Summaries.create(it))
        }
    }

    private fun parse(): WakatimeSummariesHolder {
        val day = LocalDate.now()
        if (!day.isEqual(lastSavedDay)) {
            day.minusDays(1)
        }
        lastSavedDay = day

        return wakatime.parseSummaries(LocalDate.now())
    }

    private fun save(wakatimeSummaries: WakatimeSummaries) {
        val summaries = Summaries.create(wakatimeSummaries)
        dbService.addSummaries(summaries)
    }
}