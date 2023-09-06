package net.averak.cap.usecase

import net.averak.cap.domain.primitive.echo.EchoMessage
import net.averak.cap.testutils.Faker
import org.springframework.beans.factory.annotation.Autowired

class EchoUsecase_UT extends AbstractUsecase_UT {

    @Autowired
    EchoUsecase sut

    def "echo: Echoを保存できる"() {
        given:
        final message = Faker.fake(EchoMessage)

        when:
        final result = this.sut.echo(message)

        then:
        1 * this.echoRepository.save(_)

        result.message == message
    }

}