package net.averak.cap.core.logger.schema

import com.fasterxml.jackson.annotation.JsonProperty

class ApplicationDetail(
    @JsonProperty("name") val name: String?,
    @JsonProperty("version") val version: String,
) : ILogSchema {

    override fun getFieldKey(): String {
        return "application"
    }

}
