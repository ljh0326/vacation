package com.hun2.server.service.util

import com.hun2.server.domain.util.toDate
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.YearMonth
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.Date

interface DateTimeService {
    fun getNowDate(): Date
    fun getNowTime(): Long = getNowDate().time
    fun getNowLocalDateTime(): LocalDateTime = getNowZonedDateTime().toLocalDateTime()
    fun getNowLocalDate(): LocalDate = getNowLocalDateTime().toLocalDate()
    fun getNowLocalTime(): LocalTime = getNowLocalDateTime().toLocalTime()
    fun getNowZonedDateTime(): ZonedDateTime
    fun getNowYearMonth(): YearMonth = YearMonth.from(getNowLocalDateTime())

    class Fake: DateTimeService {
        // 테스트용으로 자유롭게 수정가능
        var now: ZonedDateTime = ZonedDateTime.now()

        override fun getNowDate(): Date = now.toLocalDateTime().toDate()
        override fun getNowLocalDate(): LocalDate = now.toLocalDate()
        override fun getNowZonedDateTime(): ZonedDateTime = ZonedDateTime.now(ZoneId.of("Asia/Seoul"))

    }
}