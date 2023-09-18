package net.averak.cap.testutils.randomizer.adapter.handler.schema.request

import net.averak.cap.adapter.handler.schema.request.ProjectUpsertRequest
import net.averak.cap.domain.primitive.cron_job.CronJobCommand
import net.averak.cap.domain.primitive.cron_job.CronJobExpression
import net.averak.cap.domain.primitive.project.ContainerEnvironmentVariable
import net.averak.cap.domain.primitive.project.ContainerPort
import net.averak.cap.domain.primitive.project.DockerImage
import net.averak.cap.domain.primitive.project.ProjectName
import net.averak.cap.testutils.Faker
import net.averak.cap.testutils.randomizer.IRandomizer
import org.springframework.stereotype.Component

@Component
class ProjectUpsertRequestRandomizer implements IRandomizer {

    final Class targetType = ProjectUpsertRequest.class

    @Override
    Object getRandomValue() {
        return new ProjectUpsertRequest(
            Faker.fake(ProjectName).value,
            new ProjectUpsertRequest.DockerImageRequest(
                Faker.fake(DockerImage).repositoryName,
                Faker.fake(DockerImage).tag,
            ),
            Faker.fake(ContainerPort).value,
            Faker.dice(5).collect {
                final variable = Faker.fake(ContainerEnvironmentVariable)
                return new ProjectUpsertRequest.ContainerEnvironmentVariableRequest(
                    variable.name,
                    variable.value,
                    variable.isSecret(),
                )
            },
            Faker.dice(5).collect {
                new ProjectUpsertRequest.CronJobRequest(
                    Faker.fake(CronJobExpression).value,
                    Faker.fake(CronJobCommand).value,
                    new ProjectUpsertRequest.DockerImageRequest(
                        Faker.fake(DockerImage).repositoryName,
                        Faker.fake(DockerImage).tag,
                    ),
                    Faker.dice(5).collect {
                        final variable = Faker.fake(ContainerEnvironmentVariable)
                        return new ProjectUpsertRequest.ContainerEnvironmentVariableRequest(
                            variable.name,
                            variable.value,
                            variable.isSecret(),
                        )
                    }
                )
            }
        )
    }

}
