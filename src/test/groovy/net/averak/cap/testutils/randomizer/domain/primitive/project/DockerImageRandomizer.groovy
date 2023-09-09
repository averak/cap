package net.averak.cap.testutils.randomizer.domain.primitive.project

import net.averak.cap.domain.primitive.project.DockerImage
import net.averak.cap.testutils.Faker
import net.averak.cap.testutils.randomizer.IRandomizer
import org.springframework.stereotype.Component

@Component
class DockerImageRandomizer implements IRandomizer {

    final Class targetType = DockerImage.class

    @Override
    Object getRandomValue() {
        return new DockerImage(
            Faker.alphanumeric(10),
            Faker.alphanumeric(10),
        )
    }

}
