package net.averak.cap.domain.factory

import net.averak.cap.adapter.dao.entity.base.MaintenanceEntity
import net.averak.cap.domain.model.Maintenance
import net.averak.cap.domain.primitive.common.ID
import net.averak.cap.domain.primitive.maintenance.MaintenanceMemo
import net.averak.cap.domain.primitive.maintenance.MaintenanceTime

class MaintenanceFactory {

    companion object {

        fun create(entity: MaintenanceEntity): Maintenance {
            return Maintenance(
                ID(entity.id),
                MaintenanceTime(entity.openAt, entity.closeAt),
                if (entity.memo != null) MaintenanceMemo(entity.memo) else null,
            )
        }

    }

}
