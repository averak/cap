package net.averak.cap.core.exception

import org.springframework.http.HttpStatus

class BadRequestException(errorCode: ErrorCode) : AbstractException(HttpStatus.BAD_REQUEST, errorCode, null) {

    enum class ErrorCode(
        override val messageSourceKey: String
    ) : IErrorCode {

        // 汎用エラー
        VALIDATION_ERROR("bad_request.validation_error"),

        INVALID_REQUEST_PARAMETER("bad_request.invalid_request_parameter"),

        // Cron Job
        CRON_JOB_COMMAND_IS_INVALID("bad_request.cron_job.command_is_invalid"),

        CRON_JOB_EXPRESSION_IS_INVALID("bad_request.cron_job.expression_is_invalid"),

        // プロジェクト
        PROJECT_NAME_IS_INVALID("bad_request.project.name_is_invalid"),

        PROJECT_DOCKER_IMAGE_IS_INVALID("bad_request.project.docker_image_is_invalid"),

        PROJECT_CONTAINER_PORT_IS_INVALID("bad_request.project.container_port_is_invalid"),

        PROJECT_HOST_PORT_IS_INVALID("bad_request.project.host_port_is_invalid"),

        PROJECT_CONTAINER_ENVIRONMENT_VARIABLE_NAME_IS_INVALID("bad_request.project.container_environment_variable_name_is_invalid"),

        // Echo
        ECHO_MESSAGE_IS_INVALID("bad_request.echo.message_is_invalid"),

        // メンテナンス
        MAINTENANCE_TIME_IS_INVALID("bad_request.maintenance.time_is_invalid"),

        MAINTENANCE_MEMO_IS_INVALID("bad_request.maintenance.memo_is_invalid"),

        // その他
        ID_IS_INVALID("bad_request.others.id_is_invalid"),

    }

}
