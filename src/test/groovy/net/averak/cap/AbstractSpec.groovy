package net.averak.cap

import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
abstract class AbstractSpec extends Specification {

    /**
     * setup before test class
     */
    def setupSpec() {
        final var timeZone = TimeZone.getTimeZone("UTC")
        TimeZone.setDefault(timeZone)
    }

}
