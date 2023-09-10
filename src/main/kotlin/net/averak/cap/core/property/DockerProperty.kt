package net.averak.cap.core.property

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties("docker")
open class DockerProperty {

    lateinit var host: String

}
