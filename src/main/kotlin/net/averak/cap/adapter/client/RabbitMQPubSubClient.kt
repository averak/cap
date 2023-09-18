package net.averak.cap.adapter.client

import net.averak.cap.domain.client.IPubSubClient
import net.averak.cap.domain.model.Project
import net.averak.cap.infrastructure.rabbitmq.RabbitMQTopicExchange
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Component

@Component
class RabbitMQPubSubClient(
    private val rabbitTemplate: RabbitTemplate,
) : IPubSubClient {

    override fun launchProjectContainer(project: Project) {
        this.rabbitTemplate.convertAndSend(
            RabbitMQTopicExchange.DEFAULT.topicExchangeName,
            project,
        )
    }

}
