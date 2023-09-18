package net.averak.cap.core.logger.schema

import com.fasterxml.jackson.annotation.JsonProperty

class ApplicationLogSchema(
    @JsonProperty("name") val name: String?,
    @JsonProperty("version") val version: String,
) : ILogSchema {

    override fun fieldKey(): String {
        return "application"
    }

}
