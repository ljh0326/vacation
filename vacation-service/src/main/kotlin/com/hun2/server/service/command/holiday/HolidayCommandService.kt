package com.hun2.server.service.command.holiday

import java.time.LocalDate

interface HolidayCommandService {
    fun saveHoliday(date: LocalDate, description: String)
}