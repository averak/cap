package net.averak.cap.adapter.handler

import io.swagger.v3.oas.annotations.Hidden
import jakarta.validation.ConstraintViolationException
import jakarta.validation.ValidationException
import net.averak.cap.adapter.handler.schema.ErrorResponse
import net.averak.cap.core.exception.AbstractException
import net.averak.cap.core.exception.BadRequestException
import net.averak.cap.core.exception.BadRequestException.ErrorCode.INVALID_REQUEST_PARAMETER
import net.averak.cap.core.exception.BadRequestException.ErrorCode.VALIDATION_ERROR
import net.averak.cap.core.exception.InternalServerErrorException
import net.averak.cap.core.exception.NotFoundException
import net.averak.cap.core.exception.NotFoundException.ErrorCode.NOT_FOUND_API
import net.averak.cap.infrastructure.i18n.I18nUtils
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.stereotype.Controller
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@Hidden
@Controller
@RestControllerAdvice
class GlobalControllerAdvice : ResponseEntityExceptionHandler() {

    @RequestMapping("/api/**")
    fun handleApiNotFound(): ResponseEntity<Any> {
        return this.toResponseEntity(NotFoundException(NOT_FOUND_API))
    }

    @ExceptionHandler(AbstractException::class)
    fun handle(exception: AbstractException): ResponseEntity<Any> {
        return this.toResponseEntity(exception)
    }

    @ExceptionHandler(Exception::class)
    fun handle(exception: Exception): ResponseEntity<Any> {
        return this.toResponseEntity(exception)
    }

    @ExceptionHandler(ConstraintViolationException::class)
    fun handle(exception: ConstraintViolationException): ResponseEntity<Any> {
        return this.toResponseEntity(BadRequestException(INVALID_REQUEST_PARAMETER))
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    fun handle(exception: MethodArgumentTypeMismatchException): ResponseEntity<Any> {
        return this.toResponseEntity(BadRequestException(INVALID_REQUEST_PARAMETER))
    }

    @ExceptionHandler(ValidationException::class)
    fun handle(exception: ValidationException): ResponseEntity<Any> {
        if (exception.cause is AbstractException) {
            return this.toResponseEntity(exception.cause as AbstractException)
        } else {
            return this.toResponseEntity(BadRequestException(VALIDATION_ERROR))
        }
    }

    override fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException,
        headers: HttpHeaders,
        status: HttpStatusCode,
        request: WebRequest
    ): ResponseEntity<Any> {
        return this.toResponseEntity(BadRequestException(INVALID_REQUEST_PARAMETER))
    }

    override fun handleHttpMessageNotReadable(
        ex: HttpMessageNotReadableException,
        headers: HttpHeaders,
        status: HttpStatusCode,
        request: WebRequest
    ): ResponseEntity<Any> {
        return this.toResponseEntity(BadRequestException(INVALID_REQUEST_PARAMETER))
    }

    override fun handleMissingServletRequestParameter(
        ex: MissingServletRequestParameterException,
        headers: HttpHeaders,
        status: HttpStatusCode,
        request: WebRequest
    ): ResponseEntity<Any> {
        return this.toResponseEntity(BadRequestException(INVALID_REQUEST_PARAMETER))
    }

    private fun toResponseEntity(exception: Exception): ResponseEntity<Any> {
        return if (exception is AbstractException) {
            val message = I18nUtils.getMessage(exception.errorCode.messageSourceKey)
            val body = ErrorResponse(exception.errorCode.name, message)
            ResponseEntity(body, exception.httpStatus)
        } else {
            val e = InternalServerErrorException(exception)
            val message = I18nUtils.getMessage(e.errorCode.messageSourceKey)
            val body = ErrorResponse(e.errorCode.name, message)
            ResponseEntity(body, e.httpStatus)
        }
    }

}
