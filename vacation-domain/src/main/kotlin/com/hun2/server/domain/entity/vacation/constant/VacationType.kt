package com.hun2.server.domain.entity.vacation.constant

import java.time.LocalDate
import java.time.temporal.ChronoUnit

@Suppress( "NonAsciiCharacters", "EnumEntryName")
enum class VacationType {
    연차,
    반차,
    반반차
}

fun VacationType.getValue(startDate: LocalDate, endDate: LocalDate): Double {
    return when(this) {
        VacationType.연차 -> ChronoUnit.DAYS.between(startDate, endDate).toDouble() + 1
        VacationType.반차 -> 0.5
        VacationType.반반차 -> 0.25
    }
}

