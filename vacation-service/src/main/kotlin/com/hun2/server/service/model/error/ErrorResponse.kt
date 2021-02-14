package com.hun2.server.service.model.error

import com.hun2.server.service.exceptoin.ErrorCode

data class ErrorResponse(
    val code: ErrorCode,
    val message: String?
)
