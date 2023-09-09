package net.averak.cap.core.exception

import org.springframework.http.HttpStatus

class ConflictException(errorCode: ErrorCode) : AbstractException(HttpStatus.CONFLICT, errorCode, null) {

    enum class ErrorCode(
        override val messageSourceKey: String
    ) : IErrorCode {

        // プロジェクト
        PROJECT_NAME_IS_ALREADY_USED("conflict.project.name_is_already_used"),

    }

}
