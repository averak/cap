package net.averak.cap.domain.primitive.common

import de.huxhorn.sulky.ulid.ULID
import net.averak.cap.AbstractSpec
import net.averak.cap.core.exception.BadRequestException
import net.averak.cap.testutils.Faker

import static net.averak.cap.core.exception.BadRequestException.ErrorCode.ID_IS_INVALID

class ID_UT extends AbstractSpec {

    def "ID: 正常系 IDを作成できる"() {
        given:
        final idString = new ULID().nextULID()

        when:
        final result = new ID(idString)

        then:
        result.value == idString
    }

    def "ID: 正常系 引数を指定しなかった場合は、ULIDを自動生成できる"() {
        when:
        final result = new ID()

        then:
        result.value.size() == 26
    }

    def "ID: 不正な値が指定された場合はエラーを返す"() {
        when:
        new ID(value)

        then:
        final exception = thrown(BadRequestException)
        exception.errorCode == ID_IS_INVALID

        where:
        value << [
            "",
            Faker.alphanumeric(25),
            Faker.alphanumeric(27),
        ]
    }

}