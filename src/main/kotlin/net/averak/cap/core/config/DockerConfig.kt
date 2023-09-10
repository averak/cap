package net.averak.cap.core.config

import com.github.dockerjava.api.DockerClient
import com.github.dockerjava.core.DefaultDockerClientConfig
import com.github.dockerjava.core.DockerClientImpl
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient
import net.averak.cap.core.property.DockerProperty
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class DockerConfig(
    private val dockerProperty: DockerProperty,
) {

    @Bean
    open fun dockerClientConfig(): DefaultDockerClientConfig {
        return DefaultDockerClientConfig.createDefaultConfigBuilder()
            .withDockerHost(this.dockerProperty.host)
            .build()
    }

    @Bean
    open fun dockerClient(config: DefaultDockerClientConfig): DockerClient {
        val httpClient = ApacheDockerHttpClient.Builder()
            .dockerHost(config.dockerHost)
            .sslConfig(config.sslConfig)
            .build()
        return DockerClientImpl.getInstance(config, httpClient)
    }

}
