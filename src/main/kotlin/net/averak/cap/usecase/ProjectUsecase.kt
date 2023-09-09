package net.averak.cap.usecase

import net.averak.cap.core.exception.NotFoundException
import net.averak.cap.domain.model.Project
import net.averak.cap.domain.primitive.common.ID
import net.averak.cap.domain.repository.IProjectRepository
import org.springframework.stereotype.Service

@Service
class ProjectUsecase(
    private val projectRepository: IProjectRepository,
) {

    fun getProjects(): List<Project> {
        return this.projectRepository.findAll()
    }

    fun getProject(projectId: ID): Project {
        return this.projectRepository.findById(projectId)
            ?: throw NotFoundException(NotFoundException.ErrorCode.NOT_FOUND_PROJECT)
    }

    fun createProject() {
        // TODO: #13 ProjectUsecase::createProject を実装
    }

    fun editProject(projectId: ID) {
        // TODO: #13 ProjectUsecase::editProject を実装
    }

    fun deleteProject(projectID: ID) {
        // TODO: #13 ProjectUsecase::deleteProject を実装
    }

}
