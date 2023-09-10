package net.averak.cap.adapter.handler.controller

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class StaticContentController {

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

    private fun isAuthenticated(): Boolean {
        return SecurityContextHolder.getContext().authentication.authorities
            .any {
                it.authority == "ROLE_USER"
            }
    }

}
