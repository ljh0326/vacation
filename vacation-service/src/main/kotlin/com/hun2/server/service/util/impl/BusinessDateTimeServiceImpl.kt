package com.hun2.server.service.util.impl

import com.hun2.server.service.query.holiday.HolidayQueryService
import com.hun2.server.service.util.BusinessDateTimeService
import org.springframework.stereotype.Service
import java.time.DayOfWeek
import java.time.LocalDate

@Service
class BusinessDateTimeServiceImpl(
    private val holidayQueryService: HolidayQueryService
): BusinessDateTimeService {
    override fun isHoliday(date: LocalDate): Boolean {
        val isHoliday = holidayQueryService.isHoliday(date)
        val isWeekend = date.dayOfWeek in listOf(DayOfWeek.SUNDAY, DayOfWeek.SATURDAY)
        return (isHoliday || isWeekend)
    }

    override fun holidayCount(from: LocalDate, to: LocalDate): Int {
        return from.toLocalDates(to).filter { isHoliday(it) }.count()
    }
}

@Suppress("NestedLambdaShadowedImplicitParameter")
private fun LocalDate.toLocalDates(to: LocalDate): Sequence<LocalDate> {
    return generateSequence(this) {
        it.plusDays(1).takeIf { it <= to }
    }
}

