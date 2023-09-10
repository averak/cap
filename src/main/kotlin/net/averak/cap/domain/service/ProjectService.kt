package net.averak.cap.domain.service

import net.averak.cap.domain.primitive.project.ProjectName
import net.averak.cap.domain.repository.IProjectRepository
import org.springframework.stereotype.Service

@Service
open class ProjectService(
    private val projectRepository: IProjectRepository,
) {

    open fun isNameAlreadyUsed(name: ProjectName): Boolean {
        return this.projectRepository.existsByName(name)
    }

}
