package net.averak.cap.domain.primitive.maintenance

import net.averak.cap.AbstractSpec
import net.averak.cap.core.exception.BadRequestException
import net.averak.cap.testutils.Faker

import static net.averak.cap.core.exception.BadRequestException.ErrorCode.MAINTENANCE_MEMO_IS_INVALID

class MaintenanceMemo_UT extends AbstractSpec {

    def "MaintenanceMemo: 正常に作成できる"() {
        when:
        new MaintenanceMemo(value)

        then:
        noExceptionThrown()

        where:
        value << [
            Faker.alphanumeric(1),
            Faker.alphanumeric(1000),
        ]
    }

    def "MaintenanceMemo: 制約違反の場合は400エラー"() {
        when:
        new MaintenanceMemo(value)

        then:
        final exception = thrown(BadRequestException)
        exception.errorCode == MAINTENANCE_MEMO_IS_INVALID

        where:
        value << [
            "",
            Faker.alphanumeric(1001),
        ]
    }

}