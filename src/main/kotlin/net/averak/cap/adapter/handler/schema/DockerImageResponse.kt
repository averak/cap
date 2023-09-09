package net.averak.cap.adapter.handler.schema

import net.averak.cap.domain.primitive.project.DockerImage

class DockerImageResponse(
    val url: String,
    val tag: String,
) {

    constructor(dockerImage: DockerImage) : this(
        dockerImage.url,
        dockerImage.tag,
    )

}
