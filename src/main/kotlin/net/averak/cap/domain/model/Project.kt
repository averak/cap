package net.averak.cap.domain.model

import net.averak.cap.domain.primitive.common.ID
import net.averak.cap.domain.primitive.project.*

class Project(
    val id: ID,
    val name: ProjectName,
    val dockerImage: DockerImage,
    val containerPort: ContainerPort,
    val hostPort: HostPort,
    val containerStatus: ContainerStatus,
    val cronJobs: List<CronJob>,
) {

}
