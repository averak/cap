package net.averak.cap.infrastructure.rabbitmq

enum class RabbitMQQueue(
    val queueName: String,
) {

    DEFAULT("cap.default"),

}
