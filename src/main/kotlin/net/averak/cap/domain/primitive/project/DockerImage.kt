package net.averak.cap.domain.primitive.project

import net.averak.cap.core.exception.BadRequestException
import net.averak.cap.core.exception.BadRequestException.ErrorCode.PROJECT_DOCKER_IMAGE_IS_INVALID

class DockerImage(
    val url: String,
    val tag: String,
) {

    init {
        if (url.length !in 1..100 || tag.length !in 1..100) {
            throw BadRequestException(PROJECT_DOCKER_IMAGE_IS_INVALID)
        }
    }

}
