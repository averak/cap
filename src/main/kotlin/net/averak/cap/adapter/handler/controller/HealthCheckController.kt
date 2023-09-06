package net.averak.cap.adapter.handler.controller

import io.swagger.v3.oas.annotations.tags.Tag
import net.averak.cap.adapter.handler.schema.EchoResponse
import net.averak.cap.domain.primitive.echo.EchoMessage
import net.averak.cap.usecase.EchoUsecase
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Health Check", description = "ヘルスチェック")
@RestController
@RequestMapping(path = ["/api/health"], produces = [MediaType.APPLICATION_JSON_VALUE])
class HealthCheckController(
    private val echoUsecase: EchoUsecase
) {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun healthCheck(): EchoResponse {
        val message = EchoMessage("health check")
        return EchoResponse(this.echoUsecase.echo(message))
    }

}
