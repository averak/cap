package net.averak.cap.domain.primitive.cron_job

import net.averak.cap.AbstractSpec
import net.averak.cap.core.exception.BadRequestException
import net.averak.cap.testutils.Faker

import static net.averak.cap.core.exception.BadRequestException.ErrorCode.CRON_JOB_COMMAND_IS_INVALID

class CronJobCommand_UT extends AbstractSpec {

    def "CronJobCommand: 正常に作成できる"() {
        when:
        new CronJobCommand(value)

        then:
        noExceptionThrown()

        where:
        value << [
            Faker.alphanumeric(1),
            Faker.alphanumeric(500),
        ]
    }

    def "CronJobCommand: 制約違反の場合は400エラー"() {
        when:
        new CronJobCommand(value)

        then:
        final exception = thrown(BadRequestException)
        exception.errorCode == CRON_JOB_COMMAND_IS_INVALID

        where:
        value << [
            "",
            Faker.alphanumeric(501)
        ]
    }

}