package com.hun2.server.api.controller

import com.hun2.server.service.exceptoin.BaseException
import com.hun2.server.service.exceptoin.ErrorCode
import com.hun2.server.service.model.error.ErrorResponse
import mu.KotlinLogging
import org.springframework.context.MessageSource
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import java.util.Locale

@ControllerAdvice
class ControllerAdvice(
    private val messageSource: MessageSource
) {

    private val logger = KotlinLogging.logger{}

    @ExceptionHandler(BaseException::class)
    fun handleAuthException(e: BaseException, request: WebRequest, locale: Locale): ResponseEntity<ErrorResponse>{
        val message = e.message ?: getMessage(e.errorCode, locale, e.args)
        val errorResponse = ErrorResponse(e.errorCode, message)
        val status = e.errorCode.status

        logger.error{e}
        return ResponseEntity(errorResponse, status)
    }

    private fun getMessage(errorCode: ErrorCode, locale: Locale, args: Array<out Any?>? = null): String? =
        messageSource.getMessage(errorCode.toString(), args, null, locale)
}