package net.averak.cap.domain.model

import net.averak.cap.domain.primitive.common.ID
import net.averak.cap.domain.primitive.echo.EchoMessage
import java.time.LocalDateTime

class Echo(
    val id: ID,
    val message: EchoMessage,
    val timestamp: LocalDateTime,
) {

    constructor(message: EchoMessage) : this(ID(), message, LocalDateTime.now())

}
