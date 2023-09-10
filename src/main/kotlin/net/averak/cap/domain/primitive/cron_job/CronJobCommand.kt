package net.averak.cap.domain.primitive.cron_job

import net.averak.cap.core.exception.BadRequestException
import net.averak.cap.core.exception.BadRequestException.ErrorCode.CRON_JOB_COMMAND_IS_INVALID

data class CronJobCommand(val value: String) {

    init {
        if (value.isBlank() || value.length !in 1..500) {
            throw BadRequestException(CRON_JOB_COMMAND_IS_INVALID)
        }
    }

}
