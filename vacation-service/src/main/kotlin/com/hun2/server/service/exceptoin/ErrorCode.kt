package com.hun2.server.service.exceptoin
import org.springframework.http.HttpStatus

enum class ErrorCode(val status: HttpStatus = HttpStatus.BAD_REQUEST) {
    SESSION_NOT_FOUND(HttpStatus.UNAUTHORIZED),
    SESSION_EXPIRED(HttpStatus.UNAUTHORIZED),
    INSUFFICIENT_PRIVILEGES(HttpStatus.UNAUTHORIZED),
    INVALID_AUTH(HttpStatus.UNAUTHORIZED),
    INVALID_REQUEST(HttpStatus.BAD_REQUEST),
    NOT_FOUND(HttpStatus.NOT_FOUND),
    RESOURCE_NOT_FOUND(HttpStatus.BAD_REQUEST),
    GENERAL(HttpStatus.BAD_REQUEST),
    ARGUMENT_NOT_VALID(HttpStatus.BAD_REQUEST),
    UNKNOWN(HttpStatus.INTERNAL_SERVER_ERROR)
}
