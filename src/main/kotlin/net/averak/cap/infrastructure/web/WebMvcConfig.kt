package net.averak.cap.infrastructure.web

import net.averak.cap.adapter.interceptor.IRequestInterceptor
import net.averak.cap.adapter.interceptor.InterceptorPriority
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
@EnableWebSecurity
open class WebMvcConfig(
    private val interceptors: List<IRequestInterceptor>,
) : WebMvcConfigurer {

    override fun addInterceptors(registry: InterceptorRegistry) {
        this.interceptors.forEach {
            when (it.getPriority()) {
                InterceptorPriority.HIGH -> registry.addInterceptor(it).order(0)
                InterceptorPriority.MEDIUM -> registry.addInterceptor(it).order(1)
                InterceptorPriority.LOW -> registry.addInterceptor(it).order(2)
            }
        }
    }

}
