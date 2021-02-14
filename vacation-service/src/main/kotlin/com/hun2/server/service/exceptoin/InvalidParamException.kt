package com.hun2.server.service.exceptoin

class InvalidParamException(message: String): BaseException(message, ErrorCode.INVALID_REQUEST)