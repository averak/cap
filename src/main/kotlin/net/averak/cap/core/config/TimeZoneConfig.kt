package net.averak.cap.core.config

import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.format.DateTimeFormatter
import java.util.TimeZone

@Configuration
open class TimeZoneConfig {

    @Bean
    open fun timezone(): TimeZone {
        val timeZone = TimeZone.getTimeZone("UTC")
        TimeZone.setDefault(timeZone)
        return timeZone
    }

}
