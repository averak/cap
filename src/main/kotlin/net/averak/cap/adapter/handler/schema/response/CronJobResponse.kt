package net.averak.cap.adapter.handler.schema.response

import net.averak.cap.domain.model.CronJob

class CronJobResponse(
    val id: String,
    val expression: String,
    val command: String?,
    val dockerImage: DockerImageResponse,
    val containerEnvironmentVariables: List<ContainerEnvironmentVariableResponse>,
) {

    constructor(cronJob: CronJob) : this(
        cronJob.id.value,
        cronJob.expression.value,
        cronJob.command?.value,
        DockerImageResponse(cronJob.dockerImage),
        cronJob.containerEnvironmentVariables.map(::ContainerEnvironmentVariableResponse),
    )

}
