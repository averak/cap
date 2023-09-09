package net.averak.cap.adapter.repository

import net.averak.cap.AbstractDatabaseSpec
import net.averak.cap.adapter.dao.entity.base.CronJobEntity
import net.averak.cap.adapter.dao.entity.base.ProjectEntity
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
        )
        final cronEntities = DBUtils.insert(
            Fixture.of(CronJobEntity, [project_id: projectEntities[0].id]),
            Fixture.of(CronJobEntity, [project_id: projectEntities[0].id]),
            Fixture.of(CronJobEntity, [project_id: projectEntities[1].id]),
        )

        when:
        final result = this.sut.findAll()

        then:
        result*.id.value == projectEntities*.id
        result*.name.value == projectEntities*.name
        result*.dockerImage.url == projectEntities*.dockerImageUrl
        result*.dockerImage.tag == projectEntities*.dockerImageTag
        result*.containerPort.value == projectEntities*.containerPort
        result*.hostPort.value == projectEntities*.hostPort

        with(result[0].cronJobs) {
            it*.id.value == cronEntities[0..1]*.id
        }
        with(result[1].cronJobs) {
            it*.id.value == [cronEntities[2]]*.id
        }
        with(result[2].cronJobs) {
            it == []
        }
    }

}