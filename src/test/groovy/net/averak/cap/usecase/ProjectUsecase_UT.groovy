package net.averak.cap.usecase

import net.averak.cap.core.exception.NotFoundException
import net.averak.cap.domain.model.Project
import net.averak.cap.domain.primitive.common.ID
import net.averak.cap.testutils.Faker
import org.springframework.beans.factory.annotation.Autowired

import static net.averak.cap.core.exception.NotFoundException.ErrorCode.NOT_FOUND_PROJECT

class ProjectUsecase_UT extends AbstractUsecase_UT {

    @Autowired
    ProjectUsecase sut

    def "getProjects: 正常系 プロジェクトリストを取得できる"() {
        given:
        final projects = Faker.fakes(Project)

        when:
        final result = this.sut.getProjects()

        then:
        1 * this.projectRepository.findAll() >> projects
        result == projects
    }

    def "getProject: 正常系 プロジェクトを取得できる"() {
        given:
        final project = Faker.fake(Project)

        when:
        final result = this.sut.getProject(project.id)

        then:
        1 * this.projectRepository.findById(project.id) >> project
        result == project
    }

    def "getProject: 異常系 プロジェクトが存在しない場合は404エラー"() {
        given:
        final id = Faker.fake(ID)

        when:
        this.sut.getProject(id)

        then:
        1 * this.projectRepository.findById(id) >> null
        final exception = thrown(NotFoundException)
        exception.errorCode == NOT_FOUND_PROJECT
    }

}