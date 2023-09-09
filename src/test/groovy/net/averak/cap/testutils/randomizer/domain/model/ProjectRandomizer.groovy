package net.averak.cap.testutils.randomizer.domain.model

import net.averak.cap.domain.model.CronJob
import net.averak.cap.domain.model.Project
import net.averak.cap.domain.primitive.common.ID
import net.averak.cap.domain.primitive.project.*
import net.averak.cap.testutils.Faker
import net.averak.cap.testutils.randomizer.IRandomizer
import org.springframework.stereotype.Component

@Component
class ProjectRandomizer implements IRandomizer {

    final Class targetType = Project.class

    @Override
    Object getRandomValue() {
        return new Project(
            Faker.fake(ID),
            Faker.fake(ProjectName),
            Faker.fake(DockerImage),
            Faker.fake(ContainerPort),
            Faker.fake(HostPort),
            Faker.fakes(ContainerEnvironmentVariable),
            Faker.fake(ContainerStatus),
            Faker.fakes(CronJob),
            false,
        )
    }

}
