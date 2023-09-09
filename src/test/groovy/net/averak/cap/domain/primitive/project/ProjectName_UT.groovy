package net.averak.cap.domain.primitive.project

import net.averak.cap.AbstractSpec
import net.averak.cap.core.exception.BadRequestException
import net.averak.cap.testutils.Faker

import static net.averak.cap.core.exception.BadRequestException.ErrorCode.PROJECT_NAME_IS_INVALID

class ProjectName_UT extends AbstractSpec {

    def "ProjectName: 正常に作成できる"() {
        when:
        new ProjectName(value)

        then:
        noExceptionThrown()

        where:
        value << [
            Faker.alphanumeric(1),
            Faker.alphanumeric(100),
        ]
    }

    def "ProjectName: 制約違反の場合は400エラー"() {
        when:
        new ProjectName(value)

        then:
        final exception = thrown(BadRequestException)
        exception.errorCode == PROJECT_NAME_IS_INVALID

        where:
        value << [
            "",
            Faker.alphanumeric(101)
        ]
    }

}