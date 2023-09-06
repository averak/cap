package net.averak.cap.core.exception

import org.springframework.http.HttpStatus

class InternalServerErrorException(errorCode: ErrorCode, causedBy: Exception?) :
    AbstractException(HttpStatus.INTERNAL_SERVER_ERROR, errorCode, causedBy) {

    constructor(errorCode: ErrorCode) : this(errorCode, null)

    constructor(causedBy: Exception) : this(ErrorCode.UNEXPECTED_EXCEPTION, causedBy)

    enum class ErrorCode(
        override val messageSourceKey: String
    ) : IErrorCode {

        UNEXPECTED_EXCEPTION("internal_server_error.unexpected_exception"),

    }

}
