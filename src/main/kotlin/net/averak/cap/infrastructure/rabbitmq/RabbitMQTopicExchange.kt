package net.averak.cap.infrastructure.rabbitmq

enum class RabbitMQTopicExchange(
    val topicExchangeName: String,
    val routingKey: String,
) {

    DEFAULT("cap.default", "cap.default.#"),

}
