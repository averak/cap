package net.averak.cap.domain.primitive.project

import net.averak.cap.core.exception.BadRequestException
import net.averak.cap.core.exception.BadRequestException.ErrorCode.PROJECT_HOST_PORT_IS_INVALID

data class HostPort(val value: Int) {

    companion object {

        const val MIN = 50000

        const val MAX = 65535

    }

    init {
        if (value !in MIN..MAX) {
            throw BadRequestException(PROJECT_HOST_PORT_IS_INVALID)
        }
    }

}
