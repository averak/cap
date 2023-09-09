package net.averak.cap.adapter.dao.factory

import net.averak.cap.adapter.dao.entity.base.EchoEntity
import net.averak.cap.domain.model.Echo

class EchoEntityFactory {

    companion object {

        @JvmStatic
        fun create(echo: Echo): EchoEntity {
            return EchoEntity(
                echo.id.value,
                echo.message.value,
                echo.timestamp,
            )
        }

    }

}
