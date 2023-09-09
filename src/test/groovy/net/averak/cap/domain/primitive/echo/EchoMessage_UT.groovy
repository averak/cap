package net.averak.cap.domain.primitive.echo

import net.averak.cap.AbstractSpec
import net.averak.cap.core.exception.BadRequestException
import net.averak.cap.testutils.Faker

import static net.averak.cap.core.exception.BadRequestException.ErrorCode.ECHO_MESSAGE_IS_INVALID

class EchoMessage_UT extends AbstractSpec {

    def "EchoMessage: 正常に作成できる"() {
        when:
        new EchoMessage(value)

        then:
        noExceptionThrown()

        where:
        value << [
            Faker.alphanumeric(1),
            Faker.alphanumeric(100),
        ]
    }

    def "EchoMessage: 制約違反の場合は400エラー"() {
        when:
        new EchoMessage(value)

        then:
        final exception = thrown(BadRequestException)
        exception.errorCode == ECHO_MESSAGE_IS_INVALID

        where:
        value << [
            "",
            Faker.alphanumeric(101)
        ]
    }

}