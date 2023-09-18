package net.averak.cap.adapter.repository

import net.averak.cap.AbstractDatabaseSpec
import net.averak.cap.adapter.dao.entity.base.CronJobEntity
import net.averak.cap.adapter.dao.entity.base.ProjectEntity
import net.averak.cap.domain.model.Project
import net.averak.cap.domain.primitive.common.ID
import net.averak.cap.domain.primitive.project.ProjectName
import net.averak.cap.testutils.Faker
import net.averak.cap.testutils.db.DBUtils
import net.averak.cap.testutils.db.Fixture
import org.springframework.beans.factory.annotation.Autowired

class ProjectRepository_UT extends AbstractDatabaseSpec {

    @Autowired
    ProjectRepository sut

    def "findAll: 全件取得できる"() {
        given:
        final projectEntities = DBUtils.insert(
            Fixture.of(ProjectEntity),
            Fixture.of(ProjectEntity),
            Fixture.of(ProjectEntity),
            Fixture.of(ProjectEntity, [is_deleted: true]),
        )
        final cronJobEntities = DBUtils.insert(
            Fixture.of(CronJobEntity, [project_id: projectEntities[0].id]),
            Fixture.of(CronJobEntity, [project_id: projectEntities[0].id]),
            Fixture.of(CronJobEntity, [project_id: projectEntities[1].id]),
        )

        when:
        final result = this.sut.findAll()

        then:
        result*.id.value == projectEntities[0..2]*.id
        result*.name.value == projectEntities[0..2]*.name
        result*.dockerImage.repositoryName == projectEntities[0..2]*.dockerImageRepositoryName
        result*.dockerImage.tag == projectEntities[0..2]*.dockerImageTag
        result*.containerPort.value == projectEntities[0..2]*.containerPort
        result*.hostPort.value == projectEntities[0..2]*.hostPort

        with(result[0].cronJobs) {
            it*.id.value == cronJobEntities[0..1]*.id
        }
        with(result[1].cronJobs) {
            it*.id.value == [cronJobEntities[2]]*.id
        }
        with(result[2].cronJobs) {
            it == []
        }
    }

    def "findById: IDからプロジェクトを取得"() {
        given:
        final projectEntities = DBUtils.insert(
            Fixture.of(ProjectEntity),
            Fixture.of(ProjectEntity),
        )
        final cronJobEntities = DBUtils.insert(
            Fixture.of(CronJobEntity, [project_id: projectEntities[0].id]),
            Fixture.of(CronJobEntity, [project_id: projectEntities[0].id]),
            Fixture.of(CronJobEntity, [project_id: projectEntities[1].id]),
        )

        when:
        final result = this.sut.findById(new ID(projectEntities[0].id))

        then:
        result.id.value == projectEntities[0].id
        result.name.value == projectEntities[0].name
        result.dockerImage.repositoryName == projectEntities[0].dockerImageRepositoryName
        result.dockerImage.tag == projectEntities[0].dockerImageTag
        result.containerPort.value == projectEntities[0].containerPort
        result.hostPort.value == projectEntities[0].hostPort
        result.cronJobs*.id.value == cronJobEntities[0..1]*.id
    }

    def "findById: 存在しない場合はNULLを返す"() {
        given:
        DBUtils.insert(
            Fixture.of(ProjectEntity, [id: Faker.id("1").value, is_deleted: true])
        )

        when:
        final result = this.sut.findById(id)

        then:
        result == null

        where:
        id << [
            Faker.id("1"),
            Faker.id("2"),
        ]
    }

    def "save: プロジェクトを作成"() {
        given:
        final project = Faker.fake(Project)

        when:
        this.sut.save(project)

        then:
        with(sql.firstRow("SELECT * FROM project WHERE id=:id", [id: project.id.value])) {
            it.id == project.id.value
            it.name == project.name.value
            it.docker_image_repository_name == project.dockerImage.repositoryName
            it.docker_image_tag == project.dockerImage.tag
            it.container_port == project.containerPort.value
            it.host_port == project.hostPort.value
        }

        with(sql.rows("SELECT * FROM cron_job WHERE project_id=:project_id", [project_id: project.id.value])) {
            it.id == project.cronJobs*.id.value
            it.expression == project.cronJobs*.expression.value
            it.command == project.cronJobs*.command.value
            it.docker_image_repository_name == project.cronJobs*.dockerImage.repositoryName
            it.docker_image_tag == project.cronJobs*.dockerImage.tag
        }
    }

    def "save: プロジェクトを更新"() {
        given:
        final project = Faker.fake(Project)

        DBUtils.insert(Fixture.of(ProjectEntity, [id: project.id.value]))
        DBUtils.insert(
            Fixture.of(CronJobEntity, [project_id: project.id.value]),
            Fixture.of(CronJobEntity, [project_id: project.id.value]),
        )

        when:
        this.sut.save(project)

        then:
        sql.rows("SELECT * FROM project").size() == 1
        with(sql.firstRow("SELECT * FROM project WHERE id=:id", [id: project.id.value])) {
            it.id == project.id.value
            it.name == project.name.value
            it.docker_image_repository_name == project.dockerImage.repositoryName
            it.docker_image_tag == project.dockerImage.tag
            it.container_port == project.containerPort.value
            it.host_port == project.hostPort.value
        }

        with(sql.rows("SELECT * FROM cron_job WHERE project_id=:project_id", [project_id: project.id.value])) {
            it.id == project.cronJobs*.id.value
            it.expression == project.cronJobs*.expression.value
            it.command == project.cronJobs*.command.value
            it.docker_image_repository_name == project.cronJobs*.dockerImage.repositoryName
            it.docker_image_tag == project.cronJobs*.dockerImage.tag
        }
    }

    def "existsByName: プロジェクト名が存在するか判定"() {
        given:
        DBUtils.insert(
            Fixture.of(ProjectEntity, [name: "A"]),
            Fixture.of(ProjectEntity, [name: "B", is_deleted: true]),
        )

        when:
        final result = this.sut.existsByName(name)

        then:
        result == expectedResult

        where:
        name                 || expectedResult
        new ProjectName("A") || true
        new ProjectName("B") || false
        new ProjectName("C") || false
    }

}