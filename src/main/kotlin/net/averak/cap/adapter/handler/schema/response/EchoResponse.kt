package net.averak.cap.adapter.handler.schema.response

import net.averak.cap.domain.model.Echo
import java.time.LocalDateTime

class EchoResponse(
    val id: String,
    val message: String,
    val timestamp: LocalDateTime,
) {

    constructor(echo: Echo) : this(echo.id.value, echo.message.value, echo.timestamp)

}
