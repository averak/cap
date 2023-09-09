package net.averak.cap.domain.factory

import net.averak.cap.adapter.dao.entity.base.CronJobEntity
import net.averak.cap.adapter.dao.entity.extend.ContainerEnvironmentVariablesJson
import net.averak.cap.core.utils.JsonUtils
import net.averak.cap.domain.model.CronJob
import net.averak.cap.domain.primitive.common.ID
import net.averak.cap.domain.primitive.cron_job.CronJobCommand
import net.averak.cap.domain.primitive.cron_job.CronJobExpression
import net.averak.cap.domain.primitive.project.DockerImage

class CronJobFactory {

    companion object {

        fun create(entity: CronJobEntity): CronJob {
            return CronJob(
                ID(entity.id),
                CronJobExpression(entity.expression),
                CronJobCommand(entity.command),
                DockerImage(entity.dockerImageUrl, entity.dockerImageTag),
                JsonUtils.fromJson(
                    entity.containerEnvironmentVariables,
                    ContainerEnvironmentVariablesJson::class.java
                ).variables,
            )
        }

    }

}
