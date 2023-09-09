package net.averak.cap.adapter.repository

import net.averak.cap.AbstractDatabaseSpec
import net.averak.cap.adapter.dao.entity.base.CronJobEntity
import net.averak.cap.adapter.dao.entity.base.ProjectEntity
import net.averak.cap.domain.primitive.common.ID
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
        result*.dockerImage.url == projectEntities[0..2]*.dockerImageUrl
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
        result.id.value == projectEntities[0].id
        result.name.value == projectEntities[0].name
        result.dockerImage.url == projectEntities[0].dockerImageUrl
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

}