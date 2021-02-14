package com.hun2.server.service.command.user.impl

import com.hun2.server.domain.entity.user.User
import com.hun2.server.domain.repository.UserRepository
import com.hun2.server.service.command.user.UserCommandService
import com.hun2.server.service.model.user.UserCreateRequestModel
import org.springframework.stereotype.Service

@Service
class UserCommandServiceImpl(
    private val userRepository: UserRepository
): UserCommandService {
    override fun saveUser(request: UserCreateRequestModel) {
        userRepository.save(User(request.email, request.password, request.role))
    }
}