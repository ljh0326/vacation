package com.hun2.server.service.model.vacation

import com.hun2.server.domain.entity.vacation.Vacation
import com.hun2.server.domain.entity.vacation.constant.VacationType
import java.time.LocalDate

data class VacationResponseModel(
    val email: String,
    val usedVacation: List<VacationModel>,
    val usedVacationDays: Double,
    val remainingVacationDays: Double
)

data class VacationModel(
    val id: Long,
    val type: VacationType,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val comment: String
)

fun Vacation.toModel() :VacationModel {
    return VacationModel(this.id, this.type, this.startDate, this.endDate, this.comment)
}