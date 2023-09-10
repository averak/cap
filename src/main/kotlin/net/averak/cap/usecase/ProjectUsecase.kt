package net.averak.cap.usecase

import net.averak.cap.core.exception.ConflictException
import net.averak.cap.core.exception.ConflictException.ErrorCode.PROJECT_NAME_IS_ALREADY_USED
import net.averak.cap.core.exception.NotFoundException
import net.averak.cap.domain.client.IDockerClient
import net.averak.cap.domain.model.Project
import net.averak.cap.domain.primitive.common.ID
import net.averak.cap.domain.repository.IProjectRepository
import net.averak.cap.domain.service.ProjectService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
open class ProjectUsecase(
    private val projectRepository: IProjectRepository,
    private val projectService: ProjectService,
    private val dockerClient: IDockerClient,
) {

    @Transactional(readOnly = true)
    open fun getProjects(): List<Project> {
        return this.projectRepository.findAll()
    }

    @Transactional(readOnly = true)
    open fun getProject(projectId: ID): Project {
        return this.projectRepository.findById(projectId)
            ?: throw NotFoundException(NotFoundException.ErrorCode.NOT_FOUND_PROJECT)
    }

    @Transactional
    open fun createProject(project: Project) {
        if (this.projectService.isNameAlreadyUsed(project.name)) {
            throw ConflictException(PROJECT_NAME_IS_ALREADY_USED)
        }

        this.projectRepository.save(project)

        // TODO: ホストポートを割り当てる
        // TODO: コンテナを立ち上げる
    }

    @Transactional
    open fun editProject(project: Project) {
        val oldProject = this.projectRepository.findById(project.id)
            ?: throw NotFoundException(NotFoundException.ErrorCode.NOT_FOUND_PROJECT)

        if (oldProject.name != project.name && this.projectService.isNameAlreadyUsed(project.name)) {
            throw ConflictException(PROJECT_NAME_IS_ALREADY_USED)
        }

        this.projectRepository.save(project)

        // TODO: コンテナを立ち上げる
    }

    @Transactional
    open fun deleteProject(projectId: ID) {
        val project = this.projectRepository.findById(projectId)
            ?: throw NotFoundException(NotFoundException.ErrorCode.NOT_FOUND_PROJECT)
        project.delete()
        this.projectRepository.save(project)

        // TODO: コンテナを削除する
    }

}
