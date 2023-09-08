package net.averak.cap.domain.primitive.project

import net.averak.cap.AbstractSpec
import net.averak.cap.core.exception.BadRequestException
import net.averak.cap.testutils.Faker

import static net.averak.cap.core.exception.BadRequestException.ErrorCode.PROJECT_DOCKER_IMAGE_IS_INVALID

class DockerImage_UT extends AbstractSpec {

    def "DockerImage: 正常に作成できる"() {
        when:
        new DockerImage(url, tag)

        then:
        noExceptionThrown()

        where:
        url                     | tag
        Faker.alphanumeric(1)   | Faker.alphanumeric(1)
        Faker.alphanumeric(100) | Faker.alphanumeric(100)
    }

    def "DockerImage: 制約違反の場合は400エラー"() {
        when:
        new DockerImage(url, tag)

        then:
        final exception = thrown(BadRequestException)
        exception.errorCode == PROJECT_DOCKER_IMAGE_IS_INVALID

        where:
        url                     | tag
        Faker.alphanumeric(0)   | Faker.alphanumeric(1)
        Faker.alphanumeric(101) | Faker.alphanumeric(1)
        Faker.alphanumeric(1)   | Faker.alphanumeric(0)
        Faker.alphanumeric(1)   | Faker.alphanumeric(101)
    }

}