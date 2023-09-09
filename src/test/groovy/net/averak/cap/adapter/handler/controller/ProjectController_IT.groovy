package net.averak.cap.adapter.handler.controller

import net.averak.cap.adapter.dao.entity.base.CronJobEntity
import net.averak.cap.adapter.dao.entity.base.ProjectEntity
import net.averak.cap.adapter.handler.AbstractController_IT
import net.averak.cap.adapter.handler.schema.ProjectResponse
import net.averak.cap.adapter.handler.schema.ProjectsResponse
import net.averak.cap.core.exception.NotFoundException
import net.averak.cap.core.exception.UnauthorizedException
import net.averak.cap.domain.primitive.common.ID
import net.averak.cap.testutils.Faker
import net.averak.cap.testutils.db.DBUtils
import net.averak.cap.testutils.db.Fixture
import org.springframework.http.HttpStatus

import static net.averak.cap.core.exception.NotFoundException.ErrorCode.NOT_FOUND_PROJECT
import static net.averak.cap.core.exception.UnauthorizedException.ErrorCode.NOT_LOGGED_IN

class ProjectController_IT extends AbstractController_IT {

    // API PATH
    static final String BASE_PATH = "/api/projects"
    static final String GET_PROJECTS_PATH = BASE_PATH
    static final String GET_PROJECT_PATH = BASE_PATH + "/%s"

    def "プロジェクトリスト取得API: 正常系 プロジェクトリストを取得できる"() {
        given:
        this.login()

        final projectEntities = DBUtils.insert(
            Fixture.of(ProjectEntity),
            Fixture.of(ProjectEntity),
            Fixture.of(ProjectEntity),
        )
        final cronJobEntities = DBUtils.insert(
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
            it*.id == cronJobEntities[0..1]*.id
        }
        with(response.projects[1].cronJobs) {
            it*.id == [cronJobEntities[2]]*.id
        }
        with(response.projects[2].cronJobs) {
            it == []
        }
    }

    def "プロジェクトリスト取得API: 異常系 ログインしていない場合は401エラー"() {
        expect:
        final request = this.getRequest(GET_PROJECTS_PATH)
        this.execute(request, new UnauthorizedException(NOT_LOGGED_IN))
    }

    def "プロジェクト取得API: 正常系 プロジェクトを取得できる"() {
        given:
        this.login()

        final projectEntity = DBUtils.insert(Fixture.of(ProjectEntity))
        final cronJobEntities = DBUtils.insert(
            Fixture.of(CronJobEntity, [project_id: projectEntity.id]),
            Fixture.of(CronJobEntity, [project_id: projectEntity.id]),
        )

        when:
        final request = this.getRequest(String.format(GET_PROJECT_PATH, projectEntity.id))
        final response = this.execute(request, HttpStatus.OK, ProjectResponse)

        then:
        response.id == projectEntity.id
        response.id == projectEntity.id
        response.name == projectEntity.name
        response.dockerImage.url == projectEntity.dockerImageUrl
        response.dockerImage.tag == projectEntity.dockerImageTag
        response.containerPort == projectEntity.containerPort
        response.hostPort == projectEntity.hostPort
        response.cronJobs*.id == cronJobEntities*.id
    }

    def "プロジェクト取得API: 異常系 ログインしていない場合は401エラー"() {
        expect:
        final request = this.getRequest(String.format(GET_PROJECT_PATH, Faker.fake(ID).value))
        this.execute(request, new UnauthorizedException(NOT_LOGGED_IN))
    }

    def "プロジェクト取得API: 異常系 プロジェクトが存在しない場合は404エラー"() {
        given:
        this.login()

        expect:
        final request = this.getRequest(String.format(GET_PROJECT_PATH, Faker.fake(ID).value))
        this.execute(request, new NotFoundException(NOT_FOUND_PROJECT))
    }

}