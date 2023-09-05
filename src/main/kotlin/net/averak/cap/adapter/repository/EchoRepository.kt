package net.averak.cap.adapter.repository

import net.averak.cap.domain.model.Echo
import net.averak.cap.domain.repository.IEchoRepository
import org.springframework.stereotype.Repository

@Repository
class EchoRepository : IEchoRepository {

    override fun save(echo: Echo) {
    }

}