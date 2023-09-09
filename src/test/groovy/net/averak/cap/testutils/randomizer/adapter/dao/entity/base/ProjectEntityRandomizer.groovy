package net.averak.cap.testutils.randomizer.adapter.dao.entity.base

import net.averak.cap.adapter.dao.entity.base.ProjectEntity
import net.averak.cap.adapter.dao.factory.ProjectEntityFactory
import net.averak.cap.domain.model.Project
import net.averak.cap.testutils.Faker
import net.averak.cap.testutils.randomizer.IRandomizer
import org.springframework.stereotype.Component

@Component
class ProjectEntityRandomizer implements IRandomizer {

    final Class targetType = ProjectEntity.class

    @Override
    Object getRandomValue() {
        return ProjectEntityFactory.create(Faker.fake(Project))
    }

}
