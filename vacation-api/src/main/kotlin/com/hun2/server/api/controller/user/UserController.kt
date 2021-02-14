package com.hun2.server.api.controller.user

import com.hun2.server.service.command.user.UserCommandService
import com.hun2.server.service.model.user.UserCreateRequestModel
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController(
    private val userCommandService: UserCommandService
) {

    @PostMapping
    fun user(
        @RequestBody request: UserCreateRequestModel
    ) {
        userCommandService.saveUser(request)
    }
}