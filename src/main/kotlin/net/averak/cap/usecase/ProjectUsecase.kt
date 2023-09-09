package net.averak.cap.usecase

import net.averak.cap.domain.model.Project
import net.averak.cap.domain.repository.IProjectRepository
import org.springframework.stereotype.Service

@Service
class ProjectUsecase(
    private val projectRepository: IProjectRepository
) {

    fun getProjects(): List<Project> {
        return this.projectRepository.findAll()
    }

}
