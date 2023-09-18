package net.averak.cap.adapter.interceptor

import org.springframework.web.servlet.HandlerInterceptor

interface IRequestInterceptor : HandlerInterceptor {

    fun getPriority(): InterceptorPriority

}
