package net.averak.cap.domain.primitive.cron_job

import net.averak.cap.AbstractSpec
import net.averak.cap.core.exception.BadRequestException

import static net.averak.cap.core.exception.BadRequestException.ErrorCode.CRON_JOB_EXPRESSION_IS_INVALID

class CronJobExpression_UT extends AbstractSpec {

    def "CronJobExpression: 正常に作成できる"() {
        when:
        new CronJobExpression(value)

        then:
        noExceptionThrown()

        where:
        value << [
            "* * * * * *",
            "0 0 0 1 1 *",
        ]
    }

    def "CronJobExpression: 制約違反の場合は400エラー"() {
        when:
        new CronJobExpression(value)

        then:
        final exception = thrown(BadRequestException)
        exception.errorCode == CRON_JOB_EXPRESSION_IS_INVALID

        where:
        value << [
            "",
            "* * * * *",
            "* * * * * * *",
        ]
    }

}