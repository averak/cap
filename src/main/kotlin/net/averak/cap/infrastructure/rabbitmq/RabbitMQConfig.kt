package net.averak.cap.infrastructure.rabbitmq

import net.averak.cap.adapter.handler.subscriber.ProjectSubscriber
import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.TopicExchange
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class RabbitMQConfig {

    @Bean
    open fun queue(): Queue {
        return Queue(RabbitMQQueue.DEFAULT.queueName, false)
    }

    @Bean
    open fun topicExchange(): TopicExchange {
        return TopicExchange(RabbitMQTopicExchange.DEFAULT.topicExchangeName)
    }

    @Bean
    open fun binding(queue: Queue, topicExchange: TopicExchange): Binding {
        return BindingBuilder
            .bind(queue)
            .to(topicExchange)
            .with(RabbitMQTopicExchange.DEFAULT.routingKey)
    }

    @Bean
    open fun listenerAdapter(projectSubscriber: ProjectSubscriber): MessageListenerAdapter {
        return MessageListenerAdapter(projectSubscriber, ProjectSubscriber::launchContainer.name);
    }

    @Bean
    open fun container(
        connectionFactory: ConnectionFactory,
        listenerAdapter: MessageListenerAdapter,
    ): SimpleMessageListenerContainer {
        val container = SimpleMessageListenerContainer()
        container.connectionFactory = connectionFactory
        container.setQueueNames(RabbitMQQueue.DEFAULT.queueName)
        container.messageListener = listenerAdapter
        return container
    }

}
