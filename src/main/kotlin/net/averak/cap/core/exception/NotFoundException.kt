package net.averak.cap.core.exception

import org.springframework.http.HttpStatus

class NotFoundException(errorCode: ErrorCode) : AbstractException(HttpStatus.NOT_FOUND, errorCode, null) {

    enum class ErrorCode(
        override val messageSourceKey: String
    ) : IErrorCode {

        NOT_FOUND_API("not_found.api"),

    }

}
