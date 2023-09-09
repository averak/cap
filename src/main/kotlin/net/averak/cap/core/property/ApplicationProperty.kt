package net.averak.cap.core.property

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties("application")
open class ApplicationProperty {

    lateinit var name: String

    lateinit var version: String

}
