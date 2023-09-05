package net.averak.cap.testutils

import net.averak.cap.testutils.randomizer.IRandomizer
import org.apache.commons.lang3.RandomStringUtils
import org.jeasy.random.EasyRandom
import org.jeasy.random.EasyRandomParameters
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class Faker {

    private static EasyRandom easyRandom

    private static List<IRandomizer> randomizers

    @Autowired
    @SuppressWarnings(['GrMethodMayBeStatic'])
    void setupEasyRandom(List<IRandomizer> randomizers_) {
        randomizers = randomizers_
        final easyRandomParameters = new EasyRandomParameters()
        randomizers.each {
            easyRandomParameters.randomize(it.targetType, it)
        }
        easyRandom = new EasyRandom(easyRandomParameters)
    }

    static <T> T fake(final Class<T> clazz) {
        return easyRandom.nextObject(clazz)
    }

    static <T> List<T> fakes(final Class<T> clazz, final Integer size = easyRandom.nextInt(10)) {
        return easyRandom.objects(clazz, size).toList()
    }

    static String alphanumeric(final Integer length = 31) {
        return RandomStringUtils.randomAlphanumeric(length)
    }

    static String numeric(final Integer length = 31) {
        return RandomStringUtils.randomNumeric(length)
    }

}
