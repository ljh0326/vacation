package com.hun2.server.service.util

import java.time.DayOfWeek
import java.time.LocalDate

interface BusinessDateTimeService {
    fun isHoliday(date: LocalDate): Boolean
    fun holidayCount(from: LocalDate, to: LocalDate): Int

    class Fake: BusinessDateTimeService {
        override fun isHoliday(date: LocalDate): Boolean {
            return date.dayOfWeek in listOf(DayOfWeek.SUNDAY, DayOfWeek.SATURDAY)
        }

        override fun holidayCount(from: LocalDate, to: LocalDate): Int {
            return from.toLocalDates(to).filter { isHoliday(it) }.count()
        }

        @Suppress("NestedLambdaShadowedImplicitParameter")
        private fun LocalDate.toLocalDates(to: LocalDate): Sequence<LocalDate> {
            return generateSequence(this) {
                it.plusDays(1).takeIf { it <= to }
            }
        }

    }
}