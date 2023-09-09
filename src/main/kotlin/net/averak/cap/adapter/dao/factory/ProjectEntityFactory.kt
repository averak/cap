package net.averak.cap.adapter.dao.factory

import net.averak.cap.adapter.dao.entity.base.ProjectEntity
import net.averak.cap.adapter.dao.entity.extend.ContainerEnvironmentVariablesJson
import net.averak.cap.core.utils.JsonUtils
import net.averak.cap.domain.model.Project

class ProjectEntityFactory {

    companion object {

        @JvmStatic
        fun create(project: Project): ProjectEntity {
            return ProjectEntity(
                project.id.value,
                project.name.value,
                project.dockerImage.url,
                project.dockerImage.tag,
                project.containerPort.value,
                project.hostPort.value,
                project.isDeleted,
                JsonUtils.toJson(
                    ContainerEnvironmentVariablesJson(
                        project.containerEnvironmentVariables
                    )
                ),
            )
        }

    }

}
