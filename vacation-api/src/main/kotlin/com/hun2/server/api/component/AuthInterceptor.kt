package com.hun2.server.api.component

import com.hun2.server.service.exceptoin.InvalidAuthException
import com.hun2.server.service.util.AuthTokenService
import mu.KotlinLogging
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class AuthInterceptor(
    private val authTokenService: AuthTokenService
): HandlerInterceptor {

    private val logger = KotlinLogging.logger { }

    private val AUTHORIZATION_HEADER = "x-auth-token"
    private val ROLE = "role"
    private val USER = "USER"

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {

        return try {
            val token = authTokenService.destructToken(resolveToken(request))
            if(authTokenService.checkValidate(token) && request.session.getAttribute(ROLE) == USER){
                return true
            }

            return false
        } catch(e: Throwable) {
            throw InvalidAuthException(e.message?: "invalid authorization")
        }
    }

    private fun resolveToken(request: HttpServletRequest): String {
        return  request.getHeader(AUTHORIZATION_HEADER)
    }
}