package com.hun2.server.domain.repository

import com.hun2.server.domain.entity.holiday.Holiday
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDate

interface HolidayRepository: JpaRepository<Holiday, Long> {

    fun findByDate(date: LocalDate): Holiday?
}