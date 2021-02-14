package com.hun2.server.api.controller.vacation

import com.hun2.server.service.command.vacation.VacationCommandService
import com.hun2.server.service.model.vacation.VacationCreateRequestModel
import com.hun2.server.service.model.vacation.VacationResponseModel
import com.hun2.server.service.query.vacation.VacationQueryService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/vacations")
class VacationController(
    private val vacationQueryService: VacationQueryService,
    private val vacationCommandService: VacationCommandService
) {
    @GetMapping("/{userId}")
    fun vacations(
        @PathVariable userId: Long
    ): ResponseEntity<VacationResponseModel> {
        return ResponseEntity.ok(vacationQueryService.vacationDays(userId))
    }

    @PostMapping("/{userId}")
    fun saveVacation(
        @PathVariable userId: Long,
        @RequestBody requestCreate: VacationCreateRequestModel
    ): ResponseEntity<VacationResponseModel> {
        vacationCommandService.saveVacation(userId, requestCreate)
        return ResponseEntity.ok(vacationQueryService.vacationDays(userId))
    }

    @DeleteMapping("/{userId}/{vacationId}")
    fun cancelVacation(
        @PathVariable userId: Long,
        @PathVariable vacationId: Long
    ): ResponseEntity<VacationResponseModel> {
        vacationCommandService.cancelVacation(userId, vacationId)
        return ResponseEntity.ok(vacationQueryService.vacationDays(userId))
    }
}