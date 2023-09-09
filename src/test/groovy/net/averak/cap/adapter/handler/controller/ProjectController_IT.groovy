package net.averak.cap.adapter.handler.controller

import net.averak.cap.adapter.dao.entity.base.CronJobEntity
import net.averak.cap.adapter.dao.entity.base.ProjectEntity
import net.averak.cap.adapter.handler.AbstractController_IT
import net.averak.cap.adapter.handler.schema.ProjectsResponse
import net.averak.cap.testutils.db.DBUtils
import net.averak.cap.testutils.db.Fixture
import org.springframework.http.HttpStatus

class ProjectController_IT extends AbstractController_IT {

    // API PATH
    static final String BASE_PATH = "/api/projects"
    static final String GET_PROJECTS_PATH = BASE_PATH

    def "プロジェクトリスト取得API: 正常系 プロジェクトリストを取得できる"() {
        given:
        final projectEntities = DBUtils.insert(
            Fixture.of(ProjectEntity),
            Fixture.of(ProjectEntity),
            Fixture.of(ProjectEntity),
        )
        final cronEntities = DBUtils.insert(
            Fixture.of(CronJobEntity, [project_id: projectEntities[0].id]),
            Fixture.of(CronJobEntity, [project_id: projectEntities[0].id]),
            Fixture.of(CronJobEntity, [project_id: projectEntities[1].id]),
        )

        when:
        final request = this.getRequest(GET_PROJECTS_PATH)
        final response = this.execute(request, HttpStatus.OK, ProjectsResponse)

        then:
        response.projects*.id == projectEntities*.id
        response.projects*.name == projectEntities*.name
        response.projects*.dockerImage.url == projectEntities*.dockerImageUrl
        response.projects*.dockerImage.tag == projectEntities*.dockerImageTag
        response.projects*.containerPort == projectEntities*.containerPort
        response.projects*.hostPort == projectEntities*.hostPort

        with(response.projects[0].cronJobs) {
            it*.id == cronEntities[0..1]*.id
        }
        with(response.projects[1].cronJobs) {
            it*.id == [cronEntities[2]]*.id
        }
        with(response.projects[2].cronJobs) {
            it == []
        }
    }

}