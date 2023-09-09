package net.averak.cap.adapter.handler.schema

import net.averak.cap.domain.primitive.project.ContainerEnvironmentVariable

class ContainerEnvironmentVariableResponse(
    val name: String,
    val value: String,
    val isSecret: Boolean,
) {

    constructor(containerEnvironmentVariable: ContainerEnvironmentVariable) : this(
        containerEnvironmentVariable.name,
        containerEnvironmentVariable.value,
        containerEnvironmentVariable.isSecret,
    )

}
