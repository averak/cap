package net.averak.cap.domain.primitive.project

import net.averak.cap.AbstractSpec
import net.averak.cap.core.exception.BadRequestException

import static net.averak.cap.core.exception.BadRequestException.ErrorCode.PROJECT_CONTAINER_PORT_IS_INVALID

class ContainerPort_UT extends AbstractSpec {

    def "ContainerPort: 正常に作成できる"() {
        when:
        new ContainerPort(value)

        then:
        noExceptionThrown()

        where:
        value << [0, 65535]
    }

    def "ContainerPort: 制約違反の場合は400エラー"() {
        when:
        new ContainerPort(value)

        then:
        final exception = thrown(BadRequestException)
        exception.errorCode == PROJECT_CONTAINER_PORT_IS_INVALID

        where:
        value << [-1, 65536]
    }

}