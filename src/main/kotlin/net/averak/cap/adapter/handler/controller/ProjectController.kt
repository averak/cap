package net.averak.cap.adapter.handler.controller

import io.swagger.v3.oas.annotations.tags.Tag
import net.averak.cap.adapter.handler.schema.request.ProjectUpsertRequest
import net.averak.cap.adapter.handler.schema.response.ProjectResponse
import net.averak.cap.adapter.handler.schema.response.ProjectsResponse
import net.averak.cap.domain.factory.ProjectFactory
import net.averak.cap.domain.primitive.common.ID
import net.averak.cap.usecase.ProjectUsecase
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

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

    @GetMapping("/{project_id}")
    @ResponseStatus(HttpStatus.OK)
    fun getProject(@PathVariable("project_id") projectId: String): ProjectResponse {
        return ProjectResponse(this.projectUsecase.getProject(ID(projectId)))
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createProject(@RequestBody requestBody: ProjectUpsertRequest) {
        this.projectUsecase.createProject(ProjectFactory.create(ID(), requestBody))
    }

    @PutMapping("/{project_id}")
    @ResponseStatus(HttpStatus.OK)
    fun editProject(
        @PathVariable("project_id") projectId: String,
        @RequestBody requestBody: ProjectUpsertRequest,
    ) {
        this.projectUsecase.editProject(ProjectFactory.create(ID(projectId), requestBody))
    }

    @DeleteMapping("/{project_id}")
    @ResponseStatus(HttpStatus.OK)
    fun deleteProject(@PathVariable("project_id") projectId: String) {
        this.projectUsecase.deleteProject(ID(projectId))
    }

}
