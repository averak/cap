package net.averak.cap.adapter.repository

import net.averak.cap.adapter.dao.mapper.extend.ProjectMapper
import net.averak.cap.domain.factory.ProjectFactory
import net.averak.cap.domain.model.Project
import net.averak.cap.domain.primitive.common.ID
import net.averak.cap.domain.repository.IProjectRepository
import org.springframework.stereotype.Component

@Component
class ProjectRepository(
    private val projectMapper: ProjectMapper,
) : IProjectRepository {

    override fun findAll(): List<Project> {
        return this.projectMapper.selectAll().map(ProjectFactory::create)
    }

    override fun findById(id: ID): Project? {
        return this.projectMapper.selectById(id.value).map(ProjectFactory::create).orElse(null)
    }

}
