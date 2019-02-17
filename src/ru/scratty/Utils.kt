package ru.scratty

import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun LocalDate.toISOLocalDateString(): String = format(DateTimeFormatter.ISO_LOCAL_DATE)