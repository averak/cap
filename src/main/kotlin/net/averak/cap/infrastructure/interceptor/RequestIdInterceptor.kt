package net.averak.cap.infrastructure.interceptor

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import net.averak.cap.core.utils.IDUtils
import org.springframework.stereotype.Component

@Component
class RequestIdInterceptor : IRequestInterceptor {

    companion object {

        const val REQUEST_ID_KEY = "request_id"

    }

    override fun getPriority(): InterceptorPriority {
        return InterceptorPriority.HIGH
    }

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        request.setAttribute(REQUEST_ID_KEY, IDUtils.generateUUIDv4());
        return super.preHandle(request, response, handler)
    }

}