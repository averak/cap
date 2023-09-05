package net.averak.cap.adapter.repository

import net.averak.cap.AbstractDatabaseSpec
import net.averak.cap.domain.model.Echo
import net.averak.cap.domain.primitive.echo.EchoMessage
import net.averak.cap.testutils.Faker
import org.springframework.beans.factory.annotation.Autowired

import java.time.LocalDateTime

class EchoRepository_UT extends AbstractDatabaseSpec {

    @Autowired
    EchoRepository sut

    def "save: 保存できる"() {
        given:
        final echo = Faker.fake(Echo)

        when: "作成"
        this.sut.save(echo)

        then:
        with(sql.rows("SELECT * FROM echo")) {
            it*.id == [echo.id.value]
            it*.message == [echo.message.value]
        }

        when: "更新"
        final updatedEcho = new Echo(
            echo.id,
            Faker.fake(EchoMessage),
            Faker.fake(LocalDateTime),
        )
        this.sut.save(updatedEcho)

        then:
        with(sql.rows("SELECT * FROM echo")) {
            it*.id == [echo.id.value]
            it*.message == [updatedEcho.message.value]
        }
    }

}