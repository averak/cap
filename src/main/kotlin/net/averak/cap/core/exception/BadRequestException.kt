package net.averak.cap.core.exception

import org.springframework.http.HttpStatus

class BadRequestException(errorCode: ErrorCode) : AbstractException(HttpStatus.BAD_REQUEST, errorCode, null) {

    enum class ErrorCode(
        override val messageSourceKey: String
    ) : IErrorCode

}
