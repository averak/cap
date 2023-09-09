package net.averak.cap.adapter.handler

import io.swagger.v3.oas.annotations.Hidden
import net.averak.cap.adapter.handler.schema.ErrorResponse
import net.averak.cap.core.exception.AbstractException
import net.averak.cap.core.exception.InternalServerErrorException
import net.averak.cap.core.exception.NotFoundException
import net.averak.cap.core.exception.NotFoundException.ErrorCode.NOT_FOUND_API
import net.averak.cap.infrastructure.i18n.MessageUtils
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@Hidden
@Controller
@RestControllerAdvice
class GlobalControllerAdvice : ResponseEntityExceptionHandler() {

    @RequestMapping("/api/**")
    fun handleApiNotFound(): ResponseEntity<ErrorResponse> {
        return this.toResponseEntity(NotFoundException(NOT_FOUND_API))
    }

    @ExceptionHandler(AbstractException::class)
    fun handle(exception: AbstractException): ResponseEntity<ErrorResponse> {
        return this.toResponseEntity(exception)
    }

    @ExceptionHandler(Exception::class)
    fun handle(exception: Exception): ResponseEntity<ErrorResponse> {
        return this.toResponseEntity(exception)
    }

    private fun toResponseEntity(exception: Exception): ResponseEntity<ErrorResponse> {
        return if (exception is AbstractException) {
            val message = MessageUtils.getMessage(exception.errorCode.messageSourceKey)
            val body = ErrorResponse(exception.errorCode.name, message)
            ResponseEntity(body, exception.httpStatus)
        } else {
            val e = InternalServerErrorException(exception)
            val message = MessageUtils.getMessage(e.errorCode.messageSourceKey)
            val body = ErrorResponse(e.errorCode.name, message)
            ResponseEntity(body, e.httpStatus)
        }
    }

}
