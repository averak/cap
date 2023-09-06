package net.averak.cap.domain.primitive.echo

class EchoMessage(val value: String) {

    init {
        if (value.length !in 1..100) {
            throw Exception(String.format("invalid message: %s", value))
        }
    }

}
