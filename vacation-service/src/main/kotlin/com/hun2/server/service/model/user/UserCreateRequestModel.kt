package com.hun2.server.service.model.user

import com.hun2.server.domain.entity.user.constance.Role

data class UserCreateRequestModel(
    val email: String,
    val password: String,
    val role: Role
)
