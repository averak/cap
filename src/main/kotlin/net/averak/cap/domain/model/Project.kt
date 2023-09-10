package net.averak.cap.domain.model

import net.averak.cap.domain.primitive.common.ID
import net.averak.cap.domain.primitive.project.*

class Project(
    val id: ID,
    val name: ProjectName,
    val dockerImage: DockerImage,
    val containerPort: ContainerPort,
    var hostPort: HostPort?,
    val containerEnvironmentVariables: List<ContainerEnvironmentVariable>,
    val containerStatus: ContainerStatus,
    val cronJobs: List<CronJob>,
    var isDeleted: Boolean,
) {

    fun allocateHostPort(port: HostPort) {
        this.hostPort = port
    }

    fun delete() {
        this.isDeleted = true
    }

}
