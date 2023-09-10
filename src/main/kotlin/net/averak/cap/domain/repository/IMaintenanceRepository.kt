package net.averak.cap.domain.repository

import net.averak.cap.domain.model.Maintenance
import java.time.LocalDateTime

interface IMaintenanceRepository {

    fun find(timestamp: LocalDateTime): Maintenance?

}
