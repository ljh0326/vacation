package com.hun2.server.service.util

import com.hun2.server.service.util.impl.UserToken

interface AuthTokenService {

    fun createToken(payload: UserToken): String
    fun destructToken(token: String): UserToken
    fun checkValidate(payload: UserToken): Boolean
}