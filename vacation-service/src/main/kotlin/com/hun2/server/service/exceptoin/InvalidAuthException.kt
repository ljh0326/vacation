package com.hun2.server.service.exceptoin

class InvalidAuthException(message: String): BaseException(message, ErrorCode.INVALID_AUTH)
