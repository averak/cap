package net.averak.cap.domain.client

import net.averak.cap.domain.primitive.project.DockerImage

interface IDockerClient {

    fun pull(dockerImage: DockerImage, callback: () -> Unit)

}
