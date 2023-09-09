package net.averak.cap.infrastructure.interceptor

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import net.averak.cap.core.logger.Logger
import org.springframework.stereotype.Component

@Component
class AccessLogInterceptor(
    private val logger: Logger
) : IRequestInterceptor {

    override fun getPriority(): InterceptorPriority {
        return InterceptorPriority.LOW
    }

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        this.logger.info("New http request is reached.");
        return super.preHandle(request, response, handler)
    }

}
