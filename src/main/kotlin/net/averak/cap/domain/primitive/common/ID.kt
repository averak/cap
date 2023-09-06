package net.averak.cap.domain.primitive

import de.huxhorn.sulky.ulid.ULID

class ID(val value: String) {

    init {
        if (value.length != 26) {
            throw Exception(String.format("invalid id: %s", value))
        }
    }

    constructor() : this(ULID().nextULID())

}
