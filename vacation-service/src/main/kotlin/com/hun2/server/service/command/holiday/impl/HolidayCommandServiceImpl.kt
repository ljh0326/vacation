package com.hun2.server.service.command.holiday.impl

import com.hun2.server.domain.entity.holiday.Holiday
import com.hun2.server.domain.repository.HolidayRepository
import com.hun2.server.service.command.holiday.HolidayCommandService
import com.hun2.server.service.query.holiday.HolidayQueryService
import org.springframework.stereotype.Service
import java.time.LocalDate
import javax.transaction.Transactional

@Service
class HolidayCommandServiceImpl(
    private val holidayRepository: HolidayRepository
): HolidayCommandService {

    @Transactional
    override fun saveHoliday(date: LocalDate, description: String) {
        holidayRepository.save(Holiday(date, description))
    }
}