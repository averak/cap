package net.averak.cap.domain.primitive.project

import net.averak.cap.core.exception.BadRequestException
import net.averak.cap.core.exception.BadRequestException.ErrorCode.PROJECT_CONTAINER_ENVIRONMENT_VARIABLE_NAME_IS_INVALID

class ContainerEnvironmentVariable(
    val name: String,
    val value: String,
    val isSecret: Boolean,
) {

    init {
        if (name.matches(Regex("[a-zA-Z_]+[a-zA-Z0-9_]*")).not()) {
            throw BadRequestException(PROJECT_CONTAINER_ENVIRONMENT_VARIABLE_NAME_IS_INVALID)
        }
    }

}
