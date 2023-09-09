package net.averak.cap.adapter.handler.controller

import io.swagger.v3.oas.annotations.tags.Tag
import net.averak.cap.adapter.handler.schema.ProjectResponse
import net.averak.cap.adapter.handler.schema.ProjectsResponse
import net.averak.cap.usecase.ProjectUsecase
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Project", description = "プロジェクト")
@RestController
@RequestMapping(path = ["/api/projects"], produces = [MediaType.APPLICATION_JSON_VALUE])
class ProjectController(
    private val projectUsecase: ProjectUsecase,
) {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getProjects(): ProjectsResponse {
        return ProjectsResponse(this.projectUsecase.getProjects().map(::ProjectResponse))
    }

}
