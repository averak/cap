package net.averak.cap.usecase

import net.averak.cap.domain.model.Maintenance
import net.averak.cap.domain.repository.IMaintenanceRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
open class MaintenanceUsecase(
    private val maintenanceRepository: IMaintenanceRepository,
) {

    @Transactional(readOnly = true)
    open fun getCurrentMaintenance(): Maintenance? {
        return this.maintenanceRepository.find(LocalDateTime.now())
    }

}
