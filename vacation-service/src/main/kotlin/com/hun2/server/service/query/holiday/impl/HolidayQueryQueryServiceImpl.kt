package com.hun2.server.service.query.holiday.impl

import com.hun2.server.domain.repository.HolidayRepository
import com.hun2.server.service.query.holiday.HolidayQueryService
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class HolidayQueryQueryServiceImpl(
    private val holidayRepository: HolidayRepository
): HolidayQueryService {

    override fun isHoliday(date: LocalDate): Boolean {
        return holidayRepository.findByDate(date) != null
    }
}