package com.hun2.server.domain.entity.user

import com.hun2.server.domain.entity.user.constance.Role
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Table
@Entity
class User(
    @Column(unique = true)
    val email: String,

    @Column
    val password: String,

    @Column
    @Enumerated(EnumType.STRING)
    val role: Role
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0
}