package net.averak.cap.core.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import net.averak.cap.core.property.ApplicationProperty
import org.springdoc.core.utils.SpringDocUtils
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.bind.annotation.ModelAttribute

@Configuration
open class SwaggerConfig(
    private val applicationProperty: ApplicationProperty,
) {

    init {
        SpringDocUtils.getConfig().addAnnotationsToIgnore(ModelAttribute::class.java)
    }

    @Bean
    open fun openAPI(): OpenAPI {
        val info = Info()
            .title(this.applicationProperty.name)
            .version(this.applicationProperty.version)
        return OpenAPI().info(info)
    }

}
