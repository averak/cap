package net.averak.cap.domain.service

import net.averak.cap.domain.primitive.project.ProjectName
import net.averak.cap.domain.repository.IProjectRepository
import org.springframework.stereotype.Service

@Service
class ProjectService(
    private val projectRepository: IProjectRepository,
) {

    fun isNameAlreadyUsed(name: ProjectName): Boolean {
        return this.projectRepository.existsByName(name)
    }

}
