package net.averak.cap.core.logger.schema

import com.fasterxml.jackson.annotation.JsonProperty

class DockerImageLogSchema(
    @JsonProperty("repository_name") val repositoryName: String,
    @JsonProperty("tag") val tag: String,
) : ILogSchema {

    override fun fieldKey(): String {
        return "docker_image"
    }

}
