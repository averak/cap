package net.averak.cap.testutils.randomizer.adapter.dao.entity.base

import net.averak.cap.adapter.dao.entity.base.CronJobEntity
import net.averak.cap.adapter.dao.factory.CronJobEntityFactory
import net.averak.cap.domain.model.CronJob
import net.averak.cap.domain.model.Project
import net.averak.cap.testutils.Faker
import net.averak.cap.testutils.randomizer.IRandomizer
import org.springframework.stereotype.Component

@Component
class CronJobEntityRandomizer implements IRandomizer {

    final Class targetType = CronJobEntity.class

    @Override
    Object getRandomValue() {
        return CronJobEntityFactory.create(Faker.fake(CronJob), Faker.fake(Project))
    }

}
