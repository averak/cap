package net.averak.cap.infrastructure.i18n

import net.rakugakibox.util.YamlResourceBundle
import org.springframework.context.MessageSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.ResourceBundleMessageSource
import java.util.*

@Configuration
open class I18nConfig {

    @Bean
    open fun messageSource(): MessageSource {
        val messageSource = YamlMessageSource()
        messageSource.setBasenames("i18n/messages")
        messageSource.setDefaultEncoding("UTF-8")

        return messageSource
    }

    class YamlMessageSource : ResourceBundleMessageSource() {

        override fun doGetBundle(basename: String, locale: Locale): ResourceBundle {
            return ResourceBundle.getBundle(basename, locale, YamlResourceBundle.Control.INSTANCE);
        }

    }
}
