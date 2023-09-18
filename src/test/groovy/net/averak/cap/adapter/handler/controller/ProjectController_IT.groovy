package net.averak.cap.adapter.handler.controller

import net.averak.cap.adapter.dao.entity.base.CronJobEntity
import net.averak.cap.adapter.dao.entity.base.ProjectEntity
import net.averak.cap.adapter.handler.AbstractController_IT
import net.averak.cap.adapter.handler.schema.request.ProjectUpsertRequest
import net.averak.cap.adapter.handler.schema.response.ProjectResponse
import net.averak.cap.adapter.handler.schema.response.ProjectsResponse
import net.averak.cap.core.exception.ConflictException
import net.averak.cap.core.exception.NotFoundException
import net.averak.cap.core.exception.UnauthorizedException
import net.averak.cap.domain.primitive.common.ID
import net.averak.cap.testutils.Faker
import net.averak.cap.testutils.db.DBUtils
import net.averak.cap.testutils.db.Fixture
import org.springframework.http.HttpStatus

import static net.averak.cap.core.exception.ConflictException.ErrorCode.PROJECT_NAME_IS_ALREADY_USED
import static net.averak.cap.core.exception.NotFoundException.ErrorCode.NOT_FOUND_PROJECT
import static net.averak.cap.core.exception.UnauthorizedException.ErrorCode.NOT_LOGGED_IN

class ProjectController_IT extends AbstractController_IT {

    // API PATH
    static final String BASE_PATH = "/api/projects"
    static final String GET_PROJECTS_PATH = BASE_PATH
    static final String GET_PROJECT_PATH = BASE_PATH + "/%s"
    static final String CREATE_PROJECT_PATH = BASE_PATH
    static final String EDIT_PROJECT_PATH = BASE_PATH + "/%s"
    static final String DELETE_PROJECT_PATH = BASE_PATH + "/%s"

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
        response.projects*.dockerImage.repositoryName == projectEntities*.dockerImageRepositoryName
        response.projects*.dockerImage.tag == projectEntities*.dockerImageTag
        response.projects*.containerPort == projectEntities*.containerPort
        response.projects*.hostPort == projectEntities*.hostPort

        with(response.projects[0].cronJobs) {
            it*.id == cronJobEntities[0..1]*.id
        }
        with(response.projects[1].cronJobs) {
            it*.id == [cronJobEntities[2]]*.id
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
        response.dockerImage.repositoryName == projectEntity.dockerImageRepositoryName
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

    def "プロジェクト作成API: 正常系 プロジェクトを作成できる"() {
        given:
        this.login()

        final requestBody = Faker.fake(ProjectUpsertRequest)

        when:
        final request = this.postRequest(CREATE_PROJECT_PATH, requestBody)
        this.execute(request, HttpStatus.CREATED)

        then:
        with(sql.firstRow("SELECT * FROM project")) {
            it.name == requestBody.name
            it.docker_image_repository_name == requestBody.dockerImage.repositoryName
            it.docker_image_tag == requestBody.dockerImage.tag
            it.container_port == requestBody.containerPort
        }

        with(sql.rows("SELECT * FROM cron_job")) {
            it.expression == requestBody.cronJobs*.expression
            it.command == requestBody.cronJobs*.command
            it.docker_image_repository_name == requestBody.cronJobs*.dockerImage*.repositoryName
            it.docker_image_tag == requestBody.cronJobs*.dockerImage*.tag
        }
    }

    def "プロジェクト作成API: 異常系 ログインしていない場合は401エラー"() {
        given:
        final requestBody = Faker.fake(ProjectUpsertRequest)

        expect:
        final request = this.postRequest(CREATE_PROJECT_PATH, requestBody)
        this.execute(request, new UnauthorizedException(NOT_LOGGED_IN))
    }

    def "プロジェクト作成API: 異常系 プロジェクト名が既に使用されている場合は409エラー"() {
        given:
        this.login()

        final requestBody = Faker.fake(ProjectUpsertRequest)
        DBUtils.insert(Fixture.of(ProjectEntity, [name: requestBody.name]))

        expect:
        final request = this.postRequest(CREATE_PROJECT_PATH, requestBody)
        this.execute(request, new ConflictException(PROJECT_NAME_IS_ALREADY_USED))
    }

    def "プロジェクト編集API: 正常系 プロジェクトを編集できる"() {
        given:
        this.login()

        final requestBody = Faker.fake(ProjectUpsertRequest)
        final id = Faker.fake(ID)
        DBUtils.insert(Fixture.of(ProjectEntity, [id: id.value, name: requestBody.name]))

        when:
        final request = this.putRequest(String.format(EDIT_PROJECT_PATH, id.value), requestBody)
        this.execute(request, HttpStatus.OK)

        then:
        sql.rows("SELECT * FROM project").size() == 1
        with(sql.firstRow("SELECT * FROM project WHERE id=:id", [id: id.value])) {
            it.name == requestBody.name
            it.docker_image_repository_name == requestBody.dockerImage.repositoryName
            it.docker_image_tag == requestBody.dockerImage.tag
            it.container_port == requestBody.containerPort
        }

        with(sql.rows("SELECT * FROM cron_job WHERE project_id=:project_id", [project_id: id.value])) {
            it.expression == requestBody.cronJobs*.expression
            it.command == requestBody.cronJobs*.command
            it.docker_image_repository_name == requestBody.cronJobs*.dockerImage*.repositoryName
            it.docker_image_tag == requestBody.cronJobs*.dockerImage*.tag
        }
    }

    def "プロジェクト編集API: 異常系 ログインしていない場合は401エラー"() {
        given:
        final requestBody = Faker.fake(ProjectUpsertRequest)

        expect:
        final request = this.putRequest(String.format(EDIT_PROJECT_PATH, Faker.fake(ID).value), requestBody)
        this.execute(request, new UnauthorizedException(NOT_LOGGED_IN))
    }

    def "プロジェクト編集API: 異常系 プロジェクトが存在しない場合は404エラー"() {
        given:
        this.login()

        final requestBody = Faker.fake(ProjectUpsertRequest)

        expect:
        final request = this.putRequest(String.format(EDIT_PROJECT_PATH, Faker.fake(ID).value), requestBody)
        this.execute(request, new NotFoundException(NOT_FOUND_PROJECT))
    }

    def "プロジェクト編集API: 異常系 プロジェクト名が既に使用されている場合は409エラー"() {
        given:
        this.login()

        final requestBody = Faker.fake(ProjectUpsertRequest)
        final id = Faker.fake(ID)
        DBUtils.insert(
            Fixture.of(ProjectEntity, [id: id.value]),
            Fixture.of(ProjectEntity, [name: requestBody.name]),
        )

        expect:
        final request = this.putRequest(String.format(EDIT_PROJECT_PATH, id.value), requestBody)
        this.execute(request, new ConflictException(PROJECT_NAME_IS_ALREADY_USED))
    }

    def "プロジェクト削除API: 正常系 プロジェクトを削除できる"() {
        given:
        this.login()

        final projectEntities = DBUtils.insert(
            Fixture.of(ProjectEntity),
            Fixture.of(ProjectEntity),
        )

        when:
        final request = this.deleteRequest(String.format(DELETE_PROJECT_PATH, projectEntities[0].id))
        this.execute(request, HttpStatus.OK)

        then:
        sql.rows("SELECT * FROM project WHERE is_deleted = TRUE").id == [projectEntities[0].id]
        sql.rows("SELECT * FROM project WHERE is_deleted = FALSE").id == [projectEntities[1].id]
    }

    def "プロジェクト削除API: 異常系 ログインしていない場合は401エラー"() {
        given:
        final id = Faker.fake(ID)

        expect:
        final request = this.deleteRequest(String.format(DELETE_PROJECT_PATH, id.value))
        this.execute(request, new UnauthorizedException(NOT_LOGGED_IN))
    }

    def "プロジェクト削除API: 異常系 プロジェクトが存在しない場合は404エラー"() {
        given:
        this.login()

        final id = Faker.fake(ID)

        expect:
        final request = this.deleteRequest(String.format(DELETE_PROJECT_PATH, id.value))
        this.execute(request, new NotFoundException(NOT_FOUND_PROJECT))
    }

}