package net.averak.cap.domain.primitive

import net.averak.cap.core.utils.IDUtils

class ID(val value: String) {

    init {
        if (value.length != 26) {
            throw Exception(String.format("invalid id: %s", value))
        }
    }

    constructor() : this(IDUtils.generateULID())

}
