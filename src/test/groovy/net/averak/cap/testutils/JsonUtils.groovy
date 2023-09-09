package net.averak.cap.testutils

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule

class JsonUtils {

    @SuppressWarnings('GrDeprecatedAPIUsage')
    private static final ObjectMapper objectMapper = new ObjectMapper()
        .registerModule(new KotlinModule())
        .configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true)

    static String toJson(final Object object) {
        return objectMapper.writeValueAsString(object)
    }

    static <T> T fromJson(final String json, final Class<T> clazz) {
        return objectMapper.readValue(json, clazz)
    }

}
