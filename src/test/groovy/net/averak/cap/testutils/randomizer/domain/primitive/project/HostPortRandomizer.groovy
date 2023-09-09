package net.averak.cap.testutils.randomizer.domain.primitive.project

import net.averak.cap.domain.primitive.project.HostPort
import net.averak.cap.testutils.Faker
import net.averak.cap.testutils.randomizer.IRandomizer
import org.springframework.stereotype.Component

@Component
class HostPortRandomizer implements IRandomizer {

    final Class targetType = HostPort.class

    @Override
    Object getRandomValue() {
        return new HostPort(
            Faker.integer(HostPort.MIN, HostPort.MAX),
        )
    }

}
