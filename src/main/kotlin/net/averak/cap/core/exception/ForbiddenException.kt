package net.averak.cap.core.exception

import org.springframework.http.HttpStatus

class ForbiddenException(errorCode: ErrorCode) : AbstractException(HttpStatus.FORBIDDEN, errorCode, null) {

    enum class ErrorCode(
        override val messageSourceKey: String
    ) : IErrorCode {

        USER_HAS_NO_PERMISSION("forbidden.user_has_no_permission"),

    }

}
