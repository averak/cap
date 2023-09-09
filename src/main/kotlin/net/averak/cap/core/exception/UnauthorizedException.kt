package net.averak.cap.core.exception

import org.springframework.http.HttpStatus

class UnauthorizedException(errorCode: ErrorCode) : AbstractException(HttpStatus.UNAUTHORIZED, errorCode, null) {

    enum class ErrorCode(
        override val messageSourceKey: String
    ) : IErrorCode {

        NOT_LOGGED_IN("unauthorized.not_logged_in"),

        INCORRECT_CREDENTIALS("unauthorized.incorrect_credentials"),

    }

}
