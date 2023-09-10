package net.averak.cap.infrastructure.timezone

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.*

@Configuration
open class TimeZoneConfig {

    @Bean
    open fun timezone(): TimeZone {
        val timeZone = TimeZone.getTimeZone("UTC")
        TimeZone.setDefault(timeZone)
        return timeZone
    }

}
