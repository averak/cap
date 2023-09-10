package net.averak.cap.core.logger.interceptor

import net.averak.cap.core.logger.schema.ApplicationLogSchema
import net.averak.cap.core.logger.schema.ILogSchema
import net.averak.cap.core.property.ApplicationProperty
import org.springframework.stereotype.Component

@Component
class ApplicationInfoLoggingInterceptor(
    private val applicationProperty: ApplicationProperty,
) : ILoggingInterceptor {

    override fun intercept(): ILogSchema {
        return ApplicationLogSchema(
            this.applicationProperty.name,
            this.applicationProperty.version,
        )
    }

}
