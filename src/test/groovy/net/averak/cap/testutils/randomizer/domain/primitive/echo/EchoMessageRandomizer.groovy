package net.averak.cap.testutils.randomizer.domain.primitive.echo

import net.averak.cap.domain.primitive.echo.EchoMessage
import net.averak.cap.testutils.Faker
import net.averak.cap.testutils.randomizer.IRandomizer
import org.springframework.stereotype.Component

@Component
class EchoMessageRandomizer implements IRandomizer {

    final Class targetType = EchoMessage.class

    @Override
    Object getRandomValue() {
        return new EchoMessage(
            Faker.alphanumeric(10)
        )
    }

}
