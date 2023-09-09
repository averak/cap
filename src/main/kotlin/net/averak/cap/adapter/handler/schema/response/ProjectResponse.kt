package net.averak.cap.adapter.handler.schema.response

import net.averak.cap.domain.model.Project
import net.averak.cap.domain.primitive.project.ContainerStatus

class ProjectResponse(
    val id: String,
    val name: String,
    val dockerImage: DockerImageResponse,
    val containerPort: Int,
    val hostPort: Int,
    val containerEnvironmentVariables: List<ContainerEnvironmentVariableResponse>,
    val containerStatus: ContainerStatus,
    val cronJobs: List<CronJobResponse>,
) {

    constructor(project: Project) : this(
        project.id.value,
        project.name.value,
        DockerImageResponse(project.dockerImage),
        project.containerPort.value,
        project.hostPort.value,
        project.containerEnvironmentVariables.map(::ContainerEnvironmentVariableResponse),
        project.containerStatus,
        project.cronJobs.map(::CronJobResponse),
    )

}
