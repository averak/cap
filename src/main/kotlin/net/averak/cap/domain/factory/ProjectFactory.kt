package net.averak.cap.domain.factory

import net.averak.cap.adapter.dao.entity.extend.ProjectAndCronJobsEntity
import net.averak.cap.domain.model.Project
import net.averak.cap.domain.primitive.common.ID
import net.averak.cap.domain.primitive.project.*

class ProjectFactory {

    companion object {

        fun create(entity: ProjectAndCronJobsEntity): Project {
            return Project(
                ID(entity.project.id),
                ProjectName(entity.project.name),
                DockerImage(entity.project.dockerImageUrl, entity.project.dockerImageTag),
                ContainerPort(entity.project.containerPort),
                HostPort(entity.project.hostPort),
                listOf(),
                ContainerStatus.RUNNING,
                entity.cronJobs.map(CronJobFactory::create),
            )
        }

    }

}