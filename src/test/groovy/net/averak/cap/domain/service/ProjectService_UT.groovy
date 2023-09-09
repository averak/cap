package net.averak.cap.domain.service

import net.averak.cap.domain.primitive.project.ProjectName
import net.averak.cap.testutils.Faker
import org.springframework.beans.factory.annotation.Autowired

class ProjectService_UT extends AbstractService_UT {

    @Autowired
    ProjectService sut

    def "isNameAlreadyUsed: プロジェクト名が既に使用されているか判定する"() {
        given:
        final name = Faker.fake(ProjectName)

        when:
        final result = this.sut.isNameAlreadyUsed(name)

        then:
        1 * this.projectRepository.existsByName(name) >> isExists
        result == expectedResult

        where:
        isExists || expectedResult
        true     || true
        false    || false
    }

}