package net.averak.cap.usecase

import net.averak.cap.core.exception.ConflictException
import net.averak.cap.core.exception.ConflictException.ErrorCode.PROJECT_NAME_IS_ALREADY_USED
import net.averak.cap.core.exception.NotFoundException
import net.averak.cap.domain.model.Project
import net.averak.cap.domain.primitive.common.ID
import net.averak.cap.domain.repository.IProjectRepository
import net.averak.cap.domain.service.ProjectService
import org.springframework.stereotype.Service

@Service
class ProjectUsecase(
    private val projectRepository: IProjectRepository,
    private val projectService: ProjectService,
) {

    fun getProjects(): List<Project> {
        return this.projectRepository.findAll()
    }

    fun getProject(projectId: ID): Project {
        return this.projectRepository.findById(projectId)
            ?: throw NotFoundException(NotFoundException.ErrorCode.NOT_FOUND_PROJECT)
    }

    fun createProject(project: Project) {
        if (this.projectService.isNameAlreadyUsed(project.name)) {
            throw ConflictException(PROJECT_NAME_IS_ALREADY_USED)
        }

        this.projectRepository.save(project)
    }

    fun editProject(project: Project) {
        // TODO: #13 ProjectUsecase::editProject を実装
    }

    fun deleteProject(projectID: ID) {
        // TODO: #13 ProjectUsecase::deleteProject を実装
    }

}
