package net.averak.cap.adapter.repository

import net.averak.cap.adapter.dao.factory.EchoEntityFactory
import net.averak.cap.adapter.dao.mapper.extend.EchoMapper
import net.averak.cap.domain.model.Echo
import net.averak.cap.domain.repository.IEchoRepository
import org.springframework.stereotype.Component

@Component
class EchoRepository(
    private val echoMapper: EchoMapper
) : IEchoRepository {

    override fun save(echo: Echo) {
        this.echoMapper.bulkUpsert(listOf(EchoEntityFactory.create(echo)))
    }

}
