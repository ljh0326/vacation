package com.hun2.server.api.controller

import com.hun2.server.service.model.user.UserLoginRequestModel
import com.hun2.server.service.query.user.UserQueryService
import com.hun2.server.service.util.AuthTokenService
import com.hun2.server.service.util.impl.UserToken
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpSession

@RequestMapping
@RestController
class LoginController(
    private val authTokenService: AuthTokenService,
    private val userQueryService: UserQueryService
) {

    @PostMapping("/login")
    fun login(
        httpSession: HttpSession,
        @RequestBody request: UserLoginRequestModel
    ): ResponseEntity<String> {

        val user = userQueryService.login(request)

        //세션에 권한 추가
        httpSession.setAttribute("email", user.email)
        httpSession.setAttribute("role", user.role)

        //토큰발급
        val token = authTokenService.createToken(UserToken(user.email, user.password))

        return ResponseEntity.ok(token)
    }
}