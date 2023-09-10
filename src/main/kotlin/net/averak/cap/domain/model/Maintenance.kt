package net.averak.cap.domain.model

import net.averak.cap.domain.primitive.common.ID
import net.averak.cap.domain.primitive.maintenance.MaintenanceMemo
import net.averak.cap.domain.primitive.maintenance.MaintenanceTime
import java.time.LocalDateTime

class Maintenance(
    val id: ID,
    val time: MaintenanceTime,
    val memo: MaintenanceMemo?,
) {

    /**
     * 指定されたタイムスタンプ時点で有効なメンテナンスか判定 (半開区間)
     */
    fun isEnabled(timestamp: LocalDateTime): Boolean {
        return this.time.contains(timestamp)
    }

}
