package com.hun2.server.service.query.vacation.impl

import com.hun2.server.domain.entity.vacation.Vacation
import com.hun2.server.domain.entity.vacation.constant.VacationType
import com.hun2.server.domain.entity.vacation.constant.getValue
import com.hun2.server.domain.repository.VacationRepository
import com.hun2.server.service.model.vacation.VacationResponseModel
import com.hun2.server.service.model.vacation.toModel
import com.hun2.server.service.query.user.UserQueryService
import com.hun2.server.service.query.vacation.VacationQueryService
import com.hun2.server.service.util.BusinessDateTimeService
import com.hun2.server.service.util.DateTimeService
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.Month

@Service
class VacationQueryServiceImpl(
    private val vacationRepository: VacationRepository,
    private val dateTimeService: DateTimeService,
    private val businessDateTimeService: BusinessDateTimeService,
    private val userQueryService: UserQueryService
): VacationQueryService {

    private val legalVacationDaysPerYear = 15.0

    override fun legalVacationDaysPerYear(): Double {
        return legalVacationDaysPerYear
    }

    override fun vacationDays(userId: Long): VacationResponseModel {
        val today = dateTimeService.getNowLocalDate()

        val user = userQueryService.user(userId)
        val vacations = vacationsThisYear(today, userId)
        val usedVacationDays = usedVacationDays(vacations)
        val remainingVacationDays = legalVacationDaysPerYear - usedVacationDays

        // 법정 휴가일 - 사용한 일수
        return VacationResponseModel(user.email, vacations.map { it.toModel() }, usedVacationDays, remainingVacationDays)
    }

    override fun isMaxOutHoliday(userId: Long, vacationType: VacationType, startDate: LocalDate, endDate: LocalDate): Boolean {
        val usedVacationDays = usedVacationDays(vacationsThisYear(startDate, userId))

        // 사용가능일 = 법정휴가일 + 신청일에 포함된 휴일일수(공휴일, 주말) - 사용일수
        val availableVacationDays = legalVacationDaysPerYear + businessDateTimeService.holidayCount(startDate, endDate) - usedVacationDays
        return availableVacationDays - vacationType.getValue(startDate, endDate) < 0
    }

    private fun vacationsThisYear(today: LocalDate, userId: Long): List<Vacation> {
        val from = LocalDate.of(today.year, Month.JANUARY, 1)
        val to = LocalDate.of(today.year, Month.DECEMBER, 31)

        return vacationRepository.findAllByUserIdAndStartDateBetween(userId, from, to)
    }
    
    private fun usedVacationDays(vacations: List<Vacation>): Double {
        return vacations.sumByDouble {
            // 사용한날 - 휴일일수(공휴일, 주말) 제외
            it.type.getValue(it.startDate, it.endDate) - businessDateTimeService.holidayCount(it.startDate, it.endDate)
        }
    }

}