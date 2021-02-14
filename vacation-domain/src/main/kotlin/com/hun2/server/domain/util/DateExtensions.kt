package com.hun2.server.domain.util

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date

val KST: ZoneId = ZoneId.of("Asia/Seoul")

fun Date.toLocalDateTime(): LocalDateTime = this.toInstant().atZone(KST).toLocalDateTime()
fun Date.toLocalDate(): LocalDate = this.toInstant().atZone(KST).toLocalDate()
fun LocalDateTime.toDate(): Date = Date.from(this.atZone(KST).toInstant())
fun LocalDateTime.toString(formatter: DateTimeFormatter): String = this.format(formatter).toString()
