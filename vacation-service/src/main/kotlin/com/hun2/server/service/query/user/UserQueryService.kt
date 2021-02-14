package com.hun2.server.service.query.user

import com.hun2.server.domain.entity.user.User
import com.hun2.server.domain.entity.user.constance.Role
import com.hun2.server.service.model.user.UserLoginRequestModel
import com.hun2.server.service.model.user.UserLoginResponseModel

interface UserQueryService {
    fun user(userId:Long): User
    fun login(request: UserLoginRequestModel): UserLoginResponseModel
    fun exist(email: String, password: String): Boolean

    class Fake(): UserQueryService {
        override fun user(userId: Long): User {
            return User("123@123.com", "password", Role.USER)
        }

        override fun login(request: UserLoginRequestModel): UserLoginResponseModel {
            return UserLoginResponseModel("123@123.com", "password", "USER")
        }

        override fun exist(email: String, password: String): Boolean {
            return true
        }

    }
}