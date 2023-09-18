package net.averak.cap.adapter.handler.subscriber

import net.averak.cap.domain.model.Project
import net.averak.cap.usecase.ProjectUsecase
import org.springframework.stereotype.Component

@Component
class ProjectSubscriber(
    private val projectUsecase: ProjectUsecase,
) {

    fun launchContainer(project: Project) {
        this.projectUsecase.launchProjectContainer(project)
    }

}
