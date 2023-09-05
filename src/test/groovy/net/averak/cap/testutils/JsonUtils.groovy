package net.averak.cap.testutils

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.ObjectMapper

class JsonUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper()
        .configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true)

    static String toJson(final Object object) {
        return objectMapper.writeValueAsString(object)
    }

    static <T> T fromJson(final String json, final Class<T> clazz) {
        return objectMapper.readValue(json, clazz)
    }

}
