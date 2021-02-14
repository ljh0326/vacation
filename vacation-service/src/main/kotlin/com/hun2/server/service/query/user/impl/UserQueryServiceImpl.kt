package com.hun2.server.service.query.user.impl

import com.hun2.server.domain.entity.user.User
import com.hun2.server.domain.repository.UserRepository
import com.hun2.server.service.exceptoin.UserNotFoundException
import com.hun2.server.service.model.user.UserLoginRequestModel
import com.hun2.server.service.model.user.UserLoginResponseModel
import com.hun2.server.service.query.user.UserQueryService
import org.springframework.stereotype.Service

@Service
class UserQueryServiceImpl(
    private val userRepository: UserRepository
): UserQueryService {
    override fun user(userId: Long): User {
        return userRepository.getOne(userId)
    }

    override fun login(request: UserLoginRequestModel): UserLoginResponseModel {
        val user = userRepository.findByEmailAndPassword(request.email, request.password) ?: throw UserNotFoundException()
        return UserLoginResponseModel(user.email, user.password, user.role.name)
    }

    override fun exist(email: String, password: String): Boolean {
        return userRepository.existsByEmailAndPassword(email, password)
    }
}