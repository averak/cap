package net.averak.cap.core.logger

import com.fasterxml.jackson.annotation.JsonProperty
import net.averak.cap.core.exception.AbstractException
import net.averak.cap.core.logger.interceptor.ILoggingInterceptor
import net.averak.cap.core.logger.schema.ILogSchema
import net.averak.cap.infrastructure.i18n.I18nUtils
import net.logstash.logback.argument.StructuredArgument
import net.logstash.logback.argument.StructuredArguments.entries
import net.logstash.logback.argument.StructuredArguments.value
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class Logger(
    private val interceptors: List<ILoggingInterceptor>,
) {

    private val logger = LoggerFactory.getLogger(Logger::class.java)

    fun info(message: String) {
        this.logger.info(message, this.callInterceptors())
    }

    fun info(message: String, vararg logSchemas: ILogSchema) {
        this.logger.info(
            message,
            this.convertLogSchemasToStructuredArgument(logSchemas.toList()),
            this.callInterceptors(),
        )
    }

    fun warn(exception: Exception) {
        if (exception is AbstractException) {
            this.logger.warn(
                "${exception.javaClass.simpleName} was thrown.",
                value("error_code", ErrorCodeDetail(exception)),
                this.callInterceptors(),
                exception,
            )
        } else {
            this.logger.warn(
                "${exception.javaClass.simpleName} was thrown.",
                this.callInterceptors(),
                exception,
            )
        }
    }

    fun error(exception: Exception) {
        if (exception is AbstractException) {
            this.logger.error(
                "${exception.javaClass.simpleName} was thrown.",
                value("error_code", ErrorCodeDetail(exception)),
                this.callInterceptors(),
                exception,
            )
        } else {
            this.logger.error(
                "${exception.javaClass.simpleName} was thrown.",
                this.callInterceptors(),
                exception,
            )
        }
    }

    private fun callInterceptors(): StructuredArgument {
        return this.convertLogSchemasToStructuredArgument(
            this.interceptors.mapNotNull(ILoggingInterceptor::intercept)
        )
    }

    private fun convertLogSchemasToStructuredArgument(logSchemas: List<ILogSchema>): StructuredArgument {
        val logFieldMap = HashMap<String, Any>()
        logSchemas.forEach {
            logFieldMap[it.getFieldKey()] = it
        }
        return entries(logFieldMap);
    }

    private class ErrorCodeDetail(
        @JsonProperty("code") code: String,
        @JsonProperty("message") message: String,
        @JsonProperty("caused_by") causedBy: String?,
    ) {

        constructor(exception: AbstractException) : this(
            exception.errorCode.name,
            I18nUtils.getMessage(exception.errorCode.messageSourceKey),
            exception.causedBy?.javaClass?.simpleName
        )
    }

}
