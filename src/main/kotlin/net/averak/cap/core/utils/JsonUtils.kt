package net.averak.cap.core.utils

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule

class JsonUtils {

    companion object {

        private val objectMapper = ObjectMapper()
            .registerModule(
                KotlinModule.Builder()
                    .withReflectionCacheSize(512)
                    .configure(KotlinFeature.NullToEmptyCollection, false)
                    .configure(KotlinFeature.NullToEmptyMap, false)
                    .configure(KotlinFeature.NullIsSameAsDefault, false)
                    .configure(KotlinFeature.SingletonSupport, false)
                    .configure(KotlinFeature.StrictNullChecks, false)
                    .build()
            )
            .registerModule(JavaTimeModule())
            .configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true)

        @JvmStatic
        fun toJson(value: Any?): String {
            return if (value == null) {
                "{}"
            } else {
                objectMapper.writeValueAsString(value)
            }
        }

        @JvmStatic
        fun <T> fromJson(json: String, clazz: Class<T>): T {
            return objectMapper.readValue(json, clazz)
        }

    }
}
