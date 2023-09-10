package net.averak.cap.testutils.randomizer.adapter.dao.entity.base

import net.averak.cap.adapter.dao.entity.base.MaintenanceEntity
import net.averak.cap.domain.primitive.common.ID
import net.averak.cap.domain.primitive.maintenance.MaintenanceMemo
import net.averak.cap.domain.primitive.maintenance.MaintenanceTime
import net.averak.cap.testutils.Faker
import net.averak.cap.testutils.randomizer.IRandomizer
import org.springframework.stereotype.Component

@Component
class MaintenanceEntityRandomizer implements IRandomizer {

    final Class targetType = MaintenanceEntity.class

    @Override
    Object getRandomValue() {
        final time = Faker.fake(MaintenanceTime)
        return new MaintenanceEntity(
            Faker.fake(ID).value,
            time.openAt,
            time.closeAt,
            Faker.fake(MaintenanceMemo).value,
        )
    }

}
