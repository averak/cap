package net.averak.cap.domain.primitive.maintenance

import net.averak.cap.core.exception.BadRequestException
import net.averak.cap.core.exception.BadRequestException.ErrorCode.MAINTENANCE_TIME_IS_INVALID
import java.time.LocalDateTime

class MaintenanceTime(
    val openAt: LocalDateTime,
    val closeAt: LocalDateTime,
) {

    init {
        if (openAt.isBefore(closeAt).not()) {
            throw BadRequestException(MAINTENANCE_TIME_IS_INVALID)
        }
    }

    /**
     * 指定されたタイムスタンプを含むか判定 (半開区間)
     */
    fun contains(timestamp: LocalDateTime): Boolean {
        return (this.openAt.isEqual(timestamp) || this.openAt.isBefore(timestamp)) && this.closeAt.isAfter(timestamp)
    }

}
