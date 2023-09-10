package net.averak.cap.core.logger.interceptor

import jakarta.servlet.http.HttpServletRequest
import net.averak.cap.core.logger.schema.ILogSchema
import net.averak.cap.core.logger.schema.ReachedHttpLogSchema
import net.averak.cap.infrastructure.interceptor.RequestIdInterceptor
import org.springframework.stereotype.Component

@Component
class HttpRequestLoggingInterceptor(
    private val request: HttpServletRequest,
) : ILoggingInterceptor {

    override fun intercept(): ILogSchema? {
        return try {
            ReachedHttpLogSchema(
                this.request.getAttribute(RequestIdInterceptor.REQUEST_ID_KEY)?.toString(),
                this.request.method,
                this.request.requestURI,
                this.request.queryString,
                this.request.remoteAddr,
            )
        } catch (_: Exception) {
            null
        }
    }

}
