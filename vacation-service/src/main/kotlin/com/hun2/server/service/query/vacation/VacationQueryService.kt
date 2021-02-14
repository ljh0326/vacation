package com.hun2.server.service.query.vacation

import com.hun2.server.domain.entity.vacation.constant.VacationType
import com.hun2.server.service.model.vacation.VacationResponseModel
import java.time.LocalDate

interface VacationQueryService {
    fun legalVacationDaysPerYear(): Double
    fun vacationDays(userId: Long): VacationResponseModel
    fun isMaxOutHoliday(userId: Long, vacationType: VacationType, startDate: LocalDate, endDate: LocalDate): Boolean
}