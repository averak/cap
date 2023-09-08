package net.averak.cap.infrastructure.interceptor

import org.springframework.web.servlet.HandlerInterceptor

interface IRequestInterceptor : HandlerInterceptor {

    fun getPriority(): InterceptorPriority

}
