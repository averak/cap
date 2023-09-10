package net.averak.cap.domain.primitive.project

import net.averak.cap.core.exception.BadRequestException
import net.averak.cap.core.exception.BadRequestException.ErrorCode.PROJECT_CONTAINER_PORT_IS_INVALID

class ContainerPort(val value: Int) {

    companion object {

        const val MIN = 0

        const val MAX = 65535

    }

    init {
        if (value !in MIN..MAX) {
            throw BadRequestException(PROJECT_CONTAINER_PORT_IS_INVALID)
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ContainerPort) return false

        return value == other.value
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }

}
