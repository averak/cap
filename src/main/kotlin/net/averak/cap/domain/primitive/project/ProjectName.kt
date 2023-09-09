package net.averak.cap.domain.primitive.project

import net.averak.cap.core.exception.BadRequestException
import net.averak.cap.core.exception.BadRequestException.ErrorCode.PROJECT_NAME_IS_INVALID

class ProjectName(val value: String) {

    init {
        if (value.length !in 1..100) {
            throw BadRequestException(PROJECT_NAME_IS_INVALID)
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ProjectName) return false

        return value == other.value
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }

}
