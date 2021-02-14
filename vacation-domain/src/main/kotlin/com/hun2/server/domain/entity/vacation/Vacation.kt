package com.hun2.server.domain.entity.vacation

import com.hun2.server.domain.entity.vacation.constant.VacationType
import java.time.LocalDate
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table
class Vacation(

    @Column
    val userId: Long,

    @Column
    @Enumerated(EnumType.STRING)
    val type: VacationType,

    @Column
    val startDate: LocalDate,

    @Column
    val endDate: LocalDate,

    @Column
    val comment: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0
}