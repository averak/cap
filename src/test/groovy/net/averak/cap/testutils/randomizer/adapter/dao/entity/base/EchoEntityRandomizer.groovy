package net.averak.cap.testutils.randomizer.adapter.dao.entity.base

import net.averak.cap.adapter.dao.entity.base.EchoEntity
import net.averak.cap.adapter.dao.factory.EchoEntityFactory
import net.averak.cap.domain.model.Echo
import net.averak.cap.testutils.Faker
import net.averak.cap.testutils.randomizer.IRandomizer
import org.springframework.stereotype.Component

@Component
class EchoEntityRandomizer implements IRandomizer {

    final Class targetType = EchoEntity.class

    @Override
    Object getRandomValue() {
        return EchoEntityFactory.create(Faker.fake(Echo))
    }

}
