package net.averak.cap.adapter.client

import com.github.dockerjava.api.command.PullImageResultCallback
import com.github.dockerjava.api.model.PullResponseItem
import net.averak.cap.core.logger.Logger
import net.averak.cap.core.logger.schema.DockerImageLogSchema
import net.averak.cap.domain.client.IDockerClient
import net.averak.cap.domain.primitive.project.DockerImage
import org.springframework.stereotype.Component

@Component
class DockerClient(
    private val client: com.github.dockerjava.api.DockerClient,
    private val logger: Logger,
) : IDockerClient {

    override fun pull(dockerImage: DockerImage) {
        val dockerImageLogSchema = DockerImageLogSchema(dockerImage.repositoryName, dockerImage.tag)
        this.logger.info("Start to pull docker image.", dockerImageLogSchema)

        this.client.pullImageCmd(dockerImage.repositoryName).withTag(dockerImage.tag)
            .exec(object : PullImageResultCallback() {
                override fun onNext(item: PullResponseItem?) {
                    item?.let {
                        logger.info("Docker image pull progress: ${it.status}")
                    }
                    super.onNext(item)
                }

                override fun onComplete() {
                    logger.info("Complete to pull docker image.", dockerImageLogSchema)
                }
            })
    }

}
