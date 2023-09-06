package net.averak.cap.core.exception

import org.springframework.http.HttpStatus

abstract class AbstractException(
    val httpStatus: HttpStatus,
    val errorCode: IErrorCode,
    val causedBy: Exception?,
) : RuntimeException()
