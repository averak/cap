package net.averak.cap.domain.primitive.project

import net.averak.cap.AbstractSpec
import net.averak.cap.core.exception.BadRequestException

import static net.averak.cap.core.exception.BadRequestException.ErrorCode.PROJECT_CONTAINER_ENVIRONMENT_VARIABLE_NAME_IS_INVALID

class ContainerEnvironmentVariable_UT extends AbstractSpec {

    def "ContainerEnvironmentVariable: 正常に作成できる"() {
        when:
        new ContainerEnvironmentVariable(name, "", true)

        then:
        noExceptionThrown()

        where:
        name << [
            "ENV",
            "_ENV",
            "ENV1",
            "ENV_VAR",
            "env",
        ]
    }

    def "ContainerEnvironmentVariable: 制約違反の場合は400エラー"() {
        when:
        new ContainerEnvironmentVariable(name, "", true)

        then:
        final exception = thrown(BadRequestException)
        exception.errorCode == PROJECT_CONTAINER_ENVIRONMENT_VARIABLE_NAME_IS_INVALID

        where:
        name << [
            "1ENV",
            "ENV-VAR",
            "ENV?",
        ]
    }

}