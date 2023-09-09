package net.averak.cap.domain.primitive.echo

import net.averak.cap.core.exception.BadRequestException
import net.averak.cap.core.exception.BadRequestException.ErrorCode.ECHO_MESSAGE_IS_INVALID

class EchoMessage(val value: String) {

    init {
        if (value.length !in 1..100) {
            throw BadRequestException(ECHO_MESSAGE_IS_INVALID)
        }
    }

}
