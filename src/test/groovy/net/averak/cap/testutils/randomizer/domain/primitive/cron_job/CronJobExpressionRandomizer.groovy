package net.averak.cap.testutils.randomizer.domain.primitive.cron_job

import net.averak.cap.domain.primitive.cron_job.CronJobExpression
import net.averak.cap.testutils.Faker
import net.averak.cap.testutils.randomizer.IRandomizer
import org.springframework.stereotype.Component

@Component
class CronJobExpressionRandomizer implements IRandomizer {

    final Class targetType = CronJobExpression.class

    @Override
    Object getRandomValue() {
        return new CronJobExpression(
            "${Faker.integer(0, 59)} ${Faker.integer(0, 59)} ${Faker.integer(0, 23)} ${Faker.integer(1, 31)} ${Faker.integer(1, 12)} *",
        )
    }

}
