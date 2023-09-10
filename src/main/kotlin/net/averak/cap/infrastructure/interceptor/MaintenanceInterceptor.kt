package net.averak.cap.infrastructure.interceptor

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import net.averak.cap.usecase.MaintenanceUsecase
import org.springframework.stereotype.Component

@Component
open class MaintenanceInterceptor(
    private val maintenanceUsecase: MaintenanceUsecase,
) : IRequestInterceptor {

    override fun getPriority(): InterceptorPriority {
        return InterceptorPriority.MEDIUM
    }

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        if (request.requestURI == "/api/health") {
            return true
        } else if (request.requestURI != "/maintenance") {
            val maintenance = this.maintenanceUsecase.getCurrentMaintenance()
            if (maintenance != null) {
                response.sendRedirect("/maintenance")
                return false
            }
        }

        return true
    }

}
