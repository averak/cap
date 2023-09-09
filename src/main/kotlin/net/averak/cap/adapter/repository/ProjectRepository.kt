package net.averak.cap.adapter.repository

import net.averak.cap.adapter.dao.entity.base.CronJobExample
import net.averak.cap.adapter.dao.entity.base.ProjectExample
import net.averak.cap.adapter.dao.factory.CronJobEntityFactory
import net.averak.cap.adapter.dao.factory.ProjectEntityFactory
import net.averak.cap.adapter.dao.mapper.extend.CronJobMapper
import net.averak.cap.adapter.dao.mapper.extend.ProjectMapper
import net.averak.cap.domain.factory.ProjectFactory
import net.averak.cap.domain.model.Project
import net.averak.cap.domain.primitive.common.ID
import net.averak.cap.domain.primitive.project.ProjectName
import net.averak.cap.domain.repository.IProjectRepository
import org.springframework.stereotype.Component

@Component
class ProjectRepository(
    private val projectMapper: ProjectMapper,
    private val cronJobMapper: CronJobMapper,
) : IProjectRepository {

    override fun findAll(): List<Project> {
        return this.projectMapper.selectAll().map(ProjectFactory::create)
    }

    override fun findById(id: ID): Project? {
        return this.projectMapper.selectById(id.value).map(ProjectFactory::create).orElse(null)
    }

    override fun save(project: Project) {
        this.projectMapper.upsert(ProjectEntityFactory.create(project))

        val cronJobExample = CronJobExample()
        cronJobExample.createCriteria().andProjectIdEqualTo(project.id.value)
        this.cronJobMapper.deleteByExample(cronJobExample)
        if (project.cronJobs.isNotEmpty()) {
            this.cronJobMapper.bulkInsert(CronJobEntityFactory.create(project))
        }
    }

    override fun existsByName(name: ProjectName): Boolean {
        val example = ProjectExample()
        example.createCriteria().andIsDeletedEqualTo(false).andNameEqualTo(name.value)
        return this.projectMapper.countByExample(example).toInt() != 0
    }

}
