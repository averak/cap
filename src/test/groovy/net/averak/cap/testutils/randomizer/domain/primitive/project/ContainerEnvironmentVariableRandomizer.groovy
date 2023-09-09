package net.averak.cap.testutils.randomizer.domain.primitive.project

import net.averak.cap.testutils.Faker
import net.averak.cap.testutils.randomizer.IRandomizer
import org.springframework.stereotype.Component

@Component
class ContainerEnvironmentVariableRandomizer implements IRandomizer {

    final Class targetType = ContainerEnvironmentVariableRandomizer.class

    @Override
    Object getRandomValue() {
        return new ContainerEnvironmentVariableRandomizer(
            "_" + Faker.alphanumeric(10),
            Faker.alphanumeric(10),
            Faker.fake(Boolean),
        )
    }

}
