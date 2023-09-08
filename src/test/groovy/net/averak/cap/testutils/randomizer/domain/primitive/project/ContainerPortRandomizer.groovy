package net.averak.cap.testutils.randomizer.domain.primitive.project

import net.averak.cap.domain.primitive.project.ContainerPort
import net.averak.cap.testutils.Faker
import net.averak.cap.testutils.randomizer.IRandomizer
import org.springframework.stereotype.Component

@Component
class ContainerPortRandomizer implements IRandomizer {

    final Class targetType = ContainerPort.class

    @Override
    Object getRandomValue() {
        return new ContainerPort(
            Faker.integer(ContainerPort.MIN, ContainerPort.MAX),
        )
    }

}
