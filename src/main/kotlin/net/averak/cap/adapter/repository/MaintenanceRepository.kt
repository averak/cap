package net.averak.cap.adapter.repository

import net.averak.cap.adapter.dao.entity.base.MaintenanceExample
import net.averak.cap.adapter.dao.mapper.extend.MaintenanceMapper
import net.averak.cap.domain.factory.MaintenanceFactory
import net.averak.cap.domain.model.Maintenance
import net.averak.cap.domain.repository.IMaintenanceRepository
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class MaintenanceRepository(
    private val maintenanceMapper: MaintenanceMapper,
) : IMaintenanceRepository {

    override fun find(timestamp: LocalDateTime): Maintenance? {
        val example = MaintenanceExample()
        example.createCriteria().andOpenAtLessThanOrEqualTo(timestamp).andCloseAtGreaterThan(timestamp)
        return this.maintenanceMapper.selectByExample(example)
            .map(MaintenanceFactory::create)
            .firstOrNull { it.isEnabled(timestamp) }
    }

}
