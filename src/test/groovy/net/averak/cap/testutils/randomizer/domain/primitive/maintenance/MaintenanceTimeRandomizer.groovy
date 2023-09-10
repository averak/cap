package net.averak.cap.testutils.randomizer.domain.primitive.maintenance

import net.averak.cap.domain.primitive.maintenance.MaintenanceTime
import net.averak.cap.testutils.Faker
import net.averak.cap.testutils.randomizer.IRandomizer
import org.springframework.stereotype.Component

import java.time.LocalDateTime

@Component
class MaintenanceTimeRandomizer implements IRandomizer {

    final Class targetType = MaintenanceTime.class

    @Override
    Object getRandomValue() {
        final localDateTimes = Faker.fakes(LocalDateTime, 2).stream()
            .sorted()
            .toList()
        return new MaintenanceTime(
            localDateTimes[0],
            localDateTimes[1],
        )
    }

}
