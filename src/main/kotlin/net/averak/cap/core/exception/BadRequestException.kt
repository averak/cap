package net.averak.cap.core.exception

import org.springframework.http.HttpStatus

class BadRequestException(errorCode: ErrorCode) : AbstractException(HttpStatus.BAD_REQUEST, errorCode, null) {

    enum class ErrorCode(
        override val messageSourceKey: String
    ) : IErrorCode {

        // Cron Job
        CRON_JOB_COMMAND_IS_INVALID("bad_request.cron_job.command_is_invalid"),

        CRON_JOB_EXPRESSION_IS_INVALID("bad_request.cron_job.expression_is_invalid"),

        // プロジェクト
        PROJECT_NAME_IS_INVALID("bad_request.project.name_is_invalid"),

        PROJECT_DOCKER_IMAGE_IS_INVALID("bad_request.project.docker_image_is_invalid"),

        PROJECT_CONTAINER_PORT_IS_INVALID("bad_request.project.container_port_is_invalid"),

        PROJECT_HOST_PORT_IS_INVALID("bad_request.project.host_port_is_invalid"),

        // Echo
        ECHO_MESSAGE_IS_INVALID("bad_request.echo.message_is_invalid"),

        // その他
        ID_IS_INVALID("bad_request.others.id_is_invalid")
    }

}
