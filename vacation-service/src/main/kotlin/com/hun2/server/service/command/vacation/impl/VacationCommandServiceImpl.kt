package com.hun2.server.service.command.vacation.impl

import com.hun2.server.domain.entity.vacation.Vacation
import com.hun2.server.domain.repository.VacationRepository
import com.hun2.server.service.command.vacation.VacationCommandService
import com.hun2.server.service.exceptoin.InvalidParamException
import com.hun2.server.service.exceptoin.VacationNotFoundException
import com.hun2.server.service.model.vacation.VacationCreateRequestModel
import com.hun2.server.service.query.vacation.VacationQueryService
import com.hun2.server.service.util.BusinessDateTimeService
import com.hun2.server.service.util.DateTimeService
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class VacationCommandServiceImpl(
    private val vacationRepository: VacationRepository,
    private val vacationQueryService: VacationQueryService,
    private val businessDateTimeService: BusinessDateTimeService,
    private val dateTimeService: DateTimeService
): VacationCommandService {

    @Transactional
    override fun saveVacation(userId: Long, request: VacationCreateRequestModel) {

        if (businessDateTimeService.isHoliday(request.startDate)) {
            throw InvalidParamException("공휴일이나 주말에 휴가등록을 할 수 없습니다.")
        }

        if (vacationQueryService.isMaxOutHoliday(userId, request.type, request.startDate, request.endDate?: request.startDate))
            throw InvalidParamException("휴가일수 초과사용")

        val emptyComment = "휴가사유 미입력"
        vacationRepository.save(Vacation(userId, request.type, request.startDate, request.endDate?: request.startDate, request.content ?: emptyComment))
    }

    @Transactional
    override fun cancelVacation(userId: Long, vacationId: Long) {
        val today = dateTimeService.getNowLocalDate()
        val vacation = vacationRepository.findByIdAndUserId(vacationId, userId) ?: throw VacationNotFoundException()

        if (vacation.startDate <= today)
            throw InvalidParamException("당일, 이전휴가는 취소할 수 없습니다.")

        vacationRepository.delete(vacation)
    }
}