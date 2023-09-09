package net.averak.cap.domain.factory

import net.averak.cap.adapter.dao.entity.extend.ContainerEnvironmentVariablesJson
import net.averak.cap.adapter.dao.entity.extend.ProjectWithCronJobsEntity
import net.averak.cap.adapter.handler.schema.request.ProjectUpsertRequest
import net.averak.cap.core.utils.JsonUtils
import net.averak.cap.domain.model.Project
import net.averak.cap.domain.primitive.common.ID
import net.averak.cap.domain.primitive.project.*

class ProjectFactory {

    companion object {

        fun create(entity: ProjectWithCronJobsEntity): Project {
            return Project(
                ID(entity.id),
                ProjectName(entity.name),
                DockerImage(entity.dockerImageUrl, entity.dockerImageTag),
                ContainerPort(entity.containerPort),
                HostPort(entity.hostPort),
                JsonUtils.fromJson(
                    entity.containerEnvironmentVariables,
                    ContainerEnvironmentVariablesJson::class.java
                ).variables,
                // TODO: コンテナステータスをチェックする (永続化せず、毎回 Docker API 経由で取得すること)
                ContainerStatus.RUNNING,
                entity.cronJobs.map(CronJobFactory::create),
                entity.isDeleted,
            )
        }

        fun create(id: ID, requestBody: ProjectUpsertRequest): Project {
            return Project(
                id,
                ProjectName(requestBody.name),
                DockerImage(requestBody.dockerImage.url, requestBody.dockerImage.tag),
                ContainerPort(requestBody.containerPort),
                HostPort(requestBody.hostPort),
                requestBody.containerEnvironmentVariables.map {
                    ContainerEnvironmentVariable(it.name, it.value, it.isSecret)
                },
                ContainerStatus.CREATING,
                requestBody.cronJobs.map(CronJobFactory::create),
                false,
            )
        }

    }

}
