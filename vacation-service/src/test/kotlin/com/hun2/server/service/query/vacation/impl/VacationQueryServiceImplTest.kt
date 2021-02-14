package com.hun2.server.service.query.vacation.impl
import com.hun2.server.domain.repository.VacationRepository
import org.junit.Test
import assertk.assertThat
import assertk.assertions.isEqualTo
import com.hun2.server.domain.entity.vacation.constant.VacationType
import com.hun2.server.service.command.vacation.impl.VacationCommandServiceImpl
import com.hun2.server.service.model.vacation.VacationCreateRequestModel
import com.hun2.server.service.query.user.UserQueryService
import com.hun2.server.service.util.BusinessDateTimeService
import com.hun2.server.service.util.DateTimeService
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime

@Suppress("TestFunctionName", "NonAsciiCharacters")
class VacationQueryServiceImplTest{

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
    fun 반차_사용() {
        val userId = 1L

        val vacationRequestModel = VacationCreateRequestModel(VacationType.반차, dateTimeService.getNowLocalDate(), dateTimeService.getNowLocalDate(),"반차 테스트")
        vacationCommandService.saveVacation(userId, vacationRequestModel)

        assertThat(vacationQueryService.vacationDays(userId).usedVacationDays).isEqualTo(0.5)
    }

    @Test
    fun 반반차_사용() {
        val userId = 1L

        val vacationRequestModel = VacationCreateRequestModel(VacationType.반반차, dateTimeService.getNowLocalDate(), dateTimeService.getNowLocalDate(),"반차 테스트")
        vacationCommandService.saveVacation(userId, vacationRequestModel)

        assertThat(vacationQueryService.vacationDays(userId).usedVacationDays).isEqualTo(0.25)
    }

    @Test
    fun 반반차_반차_사용() {
        val userId = 1L

        val halfhalfVacationRequestModel = VacationCreateRequestModel(VacationType.반반차, dateTimeService.getNowLocalDate(), dateTimeService.getNowLocalDate(),"반차 테스트")
        vacationCommandService.saveVacation(userId, halfhalfVacationRequestModel)

        val halfVacationRequestModel = VacationCreateRequestModel(VacationType.반차, dateTimeService.getNowLocalDate(), dateTimeService.getNowLocalDate(),"반차 테스트")
        vacationCommandService.saveVacation(userId, halfVacationRequestModel)

        assertThat(vacationQueryService.vacationDays(userId).usedVacationDays).isEqualTo(0.75)

    }
}