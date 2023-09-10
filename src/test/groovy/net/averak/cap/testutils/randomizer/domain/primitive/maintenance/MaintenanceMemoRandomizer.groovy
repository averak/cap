package net.averak.cap.testutils.randomizer.domain.primitive.maintenance

import net.averak.cap.domain.primitive.maintenance.MaintenanceMemo
import net.averak.cap.testutils.Faker
import net.averak.cap.testutils.randomizer.IRandomizer
import org.springframework.stereotype.Component

@Component
class MaintenanceMemoRandomizer implements IRandomizer {

    final Class targetType = MaintenanceMemo.class

    @Override
    Object getRandomValue() {
        return new MaintenanceMemo(
            Faker.alphanumeric(10),
        )
    }

}
