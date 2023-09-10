package net.averak.cap.domain.primitive.maintenance

import net.averak.cap.core.exception.BadRequestException
import net.averak.cap.core.exception.BadRequestException.ErrorCode.MAINTENANCE_MEMO_IS_INVALID

class MaintenanceMemo(
    val value: String,
) {

    init {
        if (value.length !in 1..1000) {
            throw BadRequestException(MAINTENANCE_MEMO_IS_INVALID)
        }
    }

}
