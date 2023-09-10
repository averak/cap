package net.averak.cap.adapter.handler.controller

import net.averak.cap.usecase.MaintenanceUsecase
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import java.time.ZoneId
import java.time.ZonedDateTime

@Controller
class StaticContentController(
    private val maintenanceUsecase: MaintenanceUsecase,
) {

    @RequestMapping("/login")
    fun login(): String {
        return if (this.isAuthenticated()) {
            "redirect:/"
        } else {
            "forward:/"
        }
    }

    @RequestMapping("/**/{path:^(?!swagger-ui)[^\\.]*}")
    fun staticContent(@PathVariable("path") path: String): String {
        return if (this.isAuthenticated()) {
            "forward:/"
        } else {
            "redirect:/login"
        }
    }

    @RequestMapping("maintenance")
    fun maintenance(model: Model): String {
        val maintenance = this.maintenanceUsecase.getCurrentMaintenance()
        return if (maintenance == null) {
            "redirect:/"
        } else {
            val localizedCloseAt = ZonedDateTime.of(maintenance.time.closeAt, ZoneId.of("UTC"))
                .withZoneSameInstant(ZoneId.of("Asia/Tokyo"))
            model.addAttribute("close_at", localizedCloseAt)
            "maintenance"
        }
    }

    private fun isAuthenticated(): Boolean {
        return SecurityContextHolder.getContext().authentication.authorities
            .any {
                it.authority == "ROLE_USER"
            }
    }

}
