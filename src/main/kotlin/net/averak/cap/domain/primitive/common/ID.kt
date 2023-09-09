package net.averak.cap.domain.primitive.common

import net.averak.cap.core.exception.BadRequestException
import net.averak.cap.core.exception.BadRequestException.ErrorCode.ID_IS_INVALID
import net.averak.cap.core.utils.IDUtils

class ID(val value: String) {

    init {
        if (value.length != 26) {
            throw BadRequestException(ID_IS_INVALID)
        }
    }

    constructor() : this(IDUtils.generateULID())

}
