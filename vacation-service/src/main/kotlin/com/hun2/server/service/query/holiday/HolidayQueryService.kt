package com.hun2.server.service.query.holiday

import java.time.LocalDate

interface HolidayQueryService {
 fun isHoliday(date: LocalDate): Boolean
}