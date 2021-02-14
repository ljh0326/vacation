package com.hun2.server.service.command.vacation.impl

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.hun2.server.domain.entity.vacation.constant.VacationType
import com.hun2.server.domain.repository.VacationRepository
import com.hun2.server.service.model.vacation.VacationCreateRequestModel
import com.hun2.server.service.query.user.UserQueryService
import com.hun2.server.service.query.vacation.impl.VacationQueryServiceImpl
import com.hun2.server.service.util.BusinessDateTimeService
import com.hun2.server.service.util.DateTimeService
import org.junit.Test
import org.junit.jupiter.api.assertThrows
import java.lang.RuntimeException
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime

@Suppress("TestFunctionName", "NonAsciiCharacters")
internal class VacationCommandServiceImplTest{

    private val vacationRepository = VacationRepository.Fake()
    private val dateTimeService = DateTimeService.Fake().apply {
        val localDate = LocalDate.of(2021, 1, 1)
        val localTime = LocalTime.of(0, 0)
        now = ZonedDateTime.of(localDate, localTime, ZoneId.of("Asia/Seoul"))
    }

    private val businessDateTimeService = BusinessDateTimeService.Fake()
    private val userQueryService = UserQueryService.Fake()
    private val vacationQueryService = VacationQueryServiceImpl(vacationRepository, dateTimeService, businessDateTimeService, userQueryService)
    private val vacationCommandService = VacationCommandServiceImpl(vacationRepository, vacationQueryService, businessDateTimeService, dateTimeService)

    @Test
    fun 최대기간_저장() {
        val userId = 1L
        val startDate = dateTimeService.getNowLocalDate()
        val endDate = startDate.plusDays(vacationQueryService.legalVacationDaysPerYear().toLong() + 5)
        val vacationRequestModel = VacationCreateRequestModel(VacationType.연차, startDate, endDate, "최대기간 저장테스트")

        vacationCommandService.saveVacation(userId = userId, request = vacationRequestModel)
        assertThat(vacationQueryService.vacationDays(userId).usedVacationDays).isEqualTo(vacationQueryService.legalVacationDaysPerYear())
    }

    @Test
    fun 최대기간초과시_에러() {
        val userId = 1L
        val startDate = dateTimeService.getNowLocalDate()
        val endDate = startDate.plusDays(vacationQueryService.legalVacationDaysPerYear().toLong() + 6)
        val vacationRequestModel = VacationCreateRequestModel(VacationType.연차, startDate, endDate, "휴가 사용일수 초과 테스트")
        assertThrows<RuntimeException> { vacationCommandService.saveVacation(userId = userId, request = vacationRequestModel) }
    }

    @Test
    fun 시작일이주말이면_에러() {
        val userId = 1L
        val startDate = dateTimeService.getNowLocalDate().plusDays(1)
        val endDate = startDate.plusDays(vacationQueryService.legalVacationDaysPerYear().toLong())
        val vacationRequestModel = VacationCreateRequestModel(VacationType.연차, startDate, endDate, "주말입력 에러 테스트")
       assertThrows<RuntimeException> { vacationCommandService.saveVacation(userId = userId, request = vacationRequestModel) }
    }

    @Test
    fun 시작일이지난휴가취소시_에러() {
        val userId = 1L
        val tempVacationId = 1L
        val startDate = dateTimeService.getNowLocalDate().minusDays(1)
        val vacationRequestModel = VacationCreateRequestModel(VacationType.반차, startDate, startDate, "지난휴가 휴가 취소시 에러")
        vacationCommandService.saveVacation(userId = userId, request = vacationRequestModel)
        assertThrows<RuntimeException> { vacationCommandService.cancelVacation(userId, tempVacationId) }
    }

    @Test
    fun 당일가취소시_에러() {
        val userId = 1L
        val tempVacationId = 1L
        val startDate = dateTimeService.getNowLocalDate()
        val vacationRequestModel = VacationCreateRequestModel(VacationType.반차, startDate, startDate, "당일휴가 휴가 취소시 에러")
        vacationCommandService.saveVacation(userId = userId, request = vacationRequestModel)
        assertThrows<RuntimeException> { vacationCommandService.cancelVacation(userId, tempVacationId) }
    }
}