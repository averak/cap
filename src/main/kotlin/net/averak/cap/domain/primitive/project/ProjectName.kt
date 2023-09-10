package net.averak.cap.domain.primitive.project

import net.averak.cap.core.exception.BadRequestException
import net.averak.cap.core.exception.BadRequestException.ErrorCode.PROJECT_NAME_IS_INVALID

data class ProjectName(val value: String) {

    init {
        if (value.length !in 1..100) {
            throw BadRequestException(PROJECT_NAME_IS_INVALID)
        }
    }

}
