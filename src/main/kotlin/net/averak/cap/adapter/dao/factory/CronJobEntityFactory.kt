package net.averak.cap.adapter.dao.factory

import net.averak.cap.adapter.dao.entity.base.CronJobEntity
import net.averak.cap.adapter.dao.entity.extend.ContainerEnvironmentVariablesJson
import net.averak.cap.core.utils.JsonUtils
import net.averak.cap.domain.model.CronJob
import net.averak.cap.domain.model.Project

class CronJobEntityFactory {

    companion object {

        @JvmStatic
        fun create(cronJob: CronJob, project: Project): CronJobEntity {
            return CronJobEntity(
                cronJob.id.value,
                project.id.value,
                cronJob.expression.value,
                cronJob.command?.value,
                cronJob.dockerImage.url,
                cronJob.dockerImage.tag,
                JsonUtils.toJson(
                    ContainerEnvironmentVariablesJson(
                        project.containerEnvironmentVariables
                    )
                ),
            )
        }

        @JvmStatic
        fun create(project: Project): List<CronJobEntity> {
            return project.cronJobs.map {
                create(it, project)
            }
        }

    }

}
