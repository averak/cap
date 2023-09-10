package net.averak.cap.domain.primitive.project

import net.averak.cap.core.exception.BadRequestException
import net.averak.cap.core.exception.BadRequestException.ErrorCode.PROJECT_HOST_PORT_IS_INVALID

class HostPort(val value: Int) {

    companion object {

        const val MIN = 50000

        const val MAX = 65535

    }

    init {
        if (value !in MIN..MAX) {
            throw BadRequestException(PROJECT_HOST_PORT_IS_INVALID)
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is HostPort) return false

        return value == other.value
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }

}
