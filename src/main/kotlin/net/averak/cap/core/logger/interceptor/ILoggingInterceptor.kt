package net.averak.cap.core.logger.interceptor

import net.averak.cap.core.logger.schema.ILogSchema

interface ILoggingInterceptor {

    fun intercept(): ILogSchema?

}
