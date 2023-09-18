package net.averak.cap.domain.model

import net.averak.cap.AbstractSpec
import net.averak.cap.domain.primitive.common.ID
import net.averak.cap.domain.primitive.project.*
import net.averak.cap.testutils.Faker

class Project_UT extends AbstractSpec {

    def "allocateHostPort: ホストポートを割り当てる"() {
        given:
        final project = new Project(
            Faker.fake(ID),
            Faker.fake(ProjectName),
            Faker.fake(DockerImage),
            Faker.fake(ContainerPort),
            null,
            Faker.fakes(ContainerEnvironmentVariable),
            Faker.fake(ContainerStatus),
            Faker.fakes(CronJob),
            false,
        )
        final port = Faker.fake(HostPort)

        when:
        project.allocateHostPort(port)

        then:
        project.hostPort == port
    }

    def "delete: 自身を削除する"() {
        given:
        final project = Faker.fake(Project)

        when:
        project.delete()

        then:
        project.isDeleted()
    }

}