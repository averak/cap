package net.averak.cap.adapter.handler.schema.request

class ProjectUpsertRequest(
    val name: String,
    val dockerImage: DockerImageRequest,
    val containerPort: Int,
    val containerEnvironmentVariables: List<ContainerEnvironmentVariableRequest>,
    val cronJobs: List<CronJobRequest>,
) {

    class DockerImageRequest(
        val repositoryName: String,
        val tag: String,
    )

    class CronJobRequest(
        val expression: String,
        val command: String?,
        val dockerImage: DockerImageRequest,
        val containerEnvironmentVariables: List<ContainerEnvironmentVariableRequest>,
    )

    class ContainerEnvironmentVariableRequest(
        val name: String,
        val value: String,
        val isSecret: Boolean,
    )

}
