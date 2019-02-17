package ru.scratty

import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun LocalDate.toWakatimeDate(): String = format(DateTimeFormatter.ISO_LOCAL_DATE)