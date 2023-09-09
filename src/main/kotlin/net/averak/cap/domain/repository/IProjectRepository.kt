package net.averak.cap.domain.repository

import net.averak.cap.domain.model.Project
import net.averak.cap.domain.primitive.common.ID
import net.averak.cap.domain.primitive.project.ProjectName

interface IProjectRepository {

    fun findAll(): List<Project>

    fun findById(id: ID): Project?

    fun save(project: Project)

    fun existsByName(name: ProjectName): Boolean

}
