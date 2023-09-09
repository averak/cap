package net.averak.cap.domain.primitive.cron_job

import net.averak.cap.core.exception.BadRequestException
import net.averak.cap.core.exception.BadRequestException.ErrorCode.CRON_JOB_EXPRESSION_IS_INVALID
import org.springframework.scheduling.support.CronExpression

class CronJobExpression(val value: String) {

    init {
        try {
            CronExpression.parse(value)
        } catch (ignored: Exception) {
            throw BadRequestException(CRON_JOB_EXPRESSION_IS_INVALID)
        }
    }

}
