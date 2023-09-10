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

        // 常時起動させる必要のあるプロジェクトのコンテナを立ち上げる
        // バッチジョブは実行時に pull & exec すれば良いので、ここでは何も行わない
        this.dockerClient.pull(project.dockerImage) {
            this.projectService.allocateHostPort(project)
            this.projectRepository.save(project)

            // TODO: #10 Dockerイメージの用意とポート割り当てが完了したので、コンテナを立ち上げる
            // TODO: #10 無理にusecaseで非同期処理を行わず、RabbitMQを導入してはどうか
        }
    }

    @Transactional
    open fun editProject(project: Project) {
        val oldProject = this.projectRepository.findById(project.id)
            ?: throw NotFoundException(NotFoundException.ErrorCode.NOT_FOUND_PROJECT)

        if (oldProject.name != project.name && this.projectService.isNameAlreadyUsed(project.name)) {
            throw ConflictException(PROJECT_NAME_IS_ALREADY_USED)
        }

        this.projectRepository.save(project)

        // TODO: #10 コンテナを立ち上げる
    }

    @Transactional
    open fun deleteProject(projectId: ID) {
        val project = this.projectRepository.findById(projectId)
            ?: throw NotFoundException(NotFoundException.ErrorCode.NOT_FOUND_PROJECT)
        project.delete()
        this.projectRepository.save(project)

        // TODO: #10 コンテナを削除する
    }

}
