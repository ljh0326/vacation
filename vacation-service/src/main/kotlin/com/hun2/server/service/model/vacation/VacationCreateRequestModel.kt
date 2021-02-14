package com.hun2.server.service.model.vacation

import com.hun2.server.domain.entity.vacation.constant.VacationType
import java.time.LocalDate

data class VacationCreateRequestModel(
    val type: VacationType,
    val startDate: LocalDate,
    val endDate: LocalDate?,
    val content: String?
)
