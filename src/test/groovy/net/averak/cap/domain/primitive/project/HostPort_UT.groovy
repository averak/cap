package net.averak.cap.domain.primitive.project

import net.averak.cap.AbstractSpec
import net.averak.cap.core.exception.BadRequestException

import static net.averak.cap.core.exception.BadRequestException.ErrorCode.PROJECT_HOST_PORT_IS_INVALID

class HostPort_UT extends AbstractSpec {

    def "HostPort: 正常に作成できる"() {
        when:
        new HostPort(value)

        then:
        noExceptionThrown()

        where:
        value << [50000, 65535]
    }

    def "HostPort: 制約違反の場合は400エラー"() {
        when:
        new HostPort(value)

        then:
        final exception = thrown(BadRequestException)
        exception.errorCode == PROJECT_HOST_PORT_IS_INVALID

        where:
        value << [49999, 65536]
    }

}