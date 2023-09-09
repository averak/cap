package net.averak.cap.infrastructure.i18n

import net.averak.cap.core.exception.InternalServerErrorException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
import org.springframework.context.NoSuchMessageException
import org.springframework.stereotype.Component
import java.util.*

@Component
class I18nUtils {

    companion object {

        private val defaultLocale = Locale.JAPANESE;

        private lateinit var messageSource: MessageSource

        @JvmStatic
        fun getMessage(messageKey: String): String {
            try {
                return messageSource.getMessage(messageKey, null, defaultLocale)
            } catch (e: NoSuchMessageException) {
                throw InternalServerErrorException(e)
            }
        }

    }

    @Autowired
    fun setMessageSource(messageSource: MessageSource) {
        I18nUtils.messageSource = messageSource
    }

}
