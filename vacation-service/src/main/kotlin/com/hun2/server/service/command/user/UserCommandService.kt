package com.hun2.server.service.command.user

import com.hun2.server.service.model.user.UserCreateRequestModel

interface UserCommandService {
    fun saveUser(request: UserCreateRequestModel)
}