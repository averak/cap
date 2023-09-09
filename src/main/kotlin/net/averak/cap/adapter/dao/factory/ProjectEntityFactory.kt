package net.averak.cap.adapter.dao.factory

import net.averak.cap.adapter.dao.entity.base.ProjectEntity
import net.averak.cap.core.utils.JsonUtils
import net.averak.cap.domain.model.Project

class ProjectEntityFactory {

    companion object {

        fun create(project: Project): ProjectEntity {
            return ProjectEntity(
                project.id.value,
                project.name.value,
                project.dockerImage.url,
                project.dockerImage.tag,
                project.containerPort.value,
                project.hostPort.value,
                JsonUtils.toJson(project.containerEnvironmentVariables),
            )
        }

    }

}
