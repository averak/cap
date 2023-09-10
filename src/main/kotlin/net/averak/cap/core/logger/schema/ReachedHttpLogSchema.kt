package net.averak.cap.core.logger.schema

import com.fasterxml.jackson.annotation.JsonProperty

class ReachedHttpLogSchema(
    @JsonProperty("id") val id: String?,
    @JsonProperty("method") val method: String,
    @JsonProperty("uri") val uri: String,
    @JsonProperty("query_string") val queryString: String?,
    @JsonProperty("ip_address") val ipAddress: String,
) : ILogSchema {

    override fun fieldKey(): String {
        return "http_request"
    }

}
