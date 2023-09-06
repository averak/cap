package net.averak.cap.domain.primitive.echo

import net.averak.cap.AbstractSpec
import net.averak.cap.testutils.Faker

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
        thrown(Exception)

        where:
        value << [
            "",
            Faker.alphanumeric(101)
        ]
    }

}