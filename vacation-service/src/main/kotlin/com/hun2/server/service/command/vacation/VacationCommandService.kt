package com.hun2.server.service.command.vacation

import com.hun2.server.service.model.vacation.VacationCreateRequestModel

interface VacationCommandService {
    fun saveVacation(userId: Long, request: VacationCreateRequestModel)
    fun cancelVacation(userId: Long, vacationId: Long)
}