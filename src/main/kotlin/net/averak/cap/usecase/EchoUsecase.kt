package net.averak.cap.usecase

import net.averak.cap.domain.model.Echo
import net.averak.cap.domain.primitive.echo.EchoMessage
import net.averak.cap.domain.repository.IEchoRepository
import org.springframework.stereotype.Service

@Service
class EchoUsecase(
    private val echoRepository: IEchoRepository
) {

    fun echo(message: EchoMessage): Echo {
        val echo = Echo(message)
        this.echoRepository.save(echo)

        return echo
    }

}
