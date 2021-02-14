package com.hun2.server.service.exceptoin

import java.lang.RuntimeException

abstract class BaseException(
    message: String,
    val errorCode: ErrorCode,
    vararg val args: Any
) : RuntimeException(message)