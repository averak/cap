package net.averak.cap.adapter.dao.factory

import net.averak.cap.adapter.dao.entity.base.CronJobEntity
import net.averak.cap.domain.model.Project

class CronJobEntityFactory {

    companion object {

        fun create(project: Project): List<CronJobEntity> {
            return project.cronJobs.map {
                CronJobEntity(
                    it.id.value,
                    project.id.value,
                    it.expression.value,
                    it.command?.value,
                    it.dockerImage.url,
                    it.dockerImage.tag,
                )
            }
        }

    }

}
