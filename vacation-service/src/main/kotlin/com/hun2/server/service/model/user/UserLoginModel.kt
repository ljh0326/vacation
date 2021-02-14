package com.hun2.server.service.model.user

data class UserLoginRequestModel(
    val email: String,
    val password: String
)

data class UserLoginResponseModel(
    val email: String,
    val password: String,
    val role: String
)