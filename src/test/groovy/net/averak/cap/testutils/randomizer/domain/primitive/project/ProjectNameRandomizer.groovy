package net.averak.cap.testutils.randomizer.domain.primitive.project

import net.averak.cap.domain.primitive.project.ProjectName
import net.averak.cap.testutils.Faker
import net.averak.cap.testutils.randomizer.IRandomizer
import org.springframework.stereotype.Component

@Component
class ProjectNameRandomizer implements IRandomizer {

    final Class targetType = ProjectName.class

    @Override
    Object getRandomValue() {
        return new ProjectName(
            Faker.alphanumeric(10),
        )
    }

}
