package net.averak.cap.testutils.randomizer.domain.primitive.cron_job

import net.averak.cap.domain.primitive.cron_job.CronJobCommand
import net.averak.cap.testutils.Faker
import net.averak.cap.testutils.randomizer.IRandomizer
import org.springframework.stereotype.Component

@Component
class CronJobCommandRandomizer implements IRandomizer {

    final Class targetType = CronJobCommand.class

    @Override
    Object getRandomValue() {
        return new CronJobCommand(
            "echo \"${Faker.alphanumeric(10)}\"",
        )
    }

}
