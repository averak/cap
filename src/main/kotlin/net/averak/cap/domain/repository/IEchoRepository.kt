package net.averak.cap.domain.repository

import net.averak.cap.domain.model.Echo

interface IEchoRepository {

    fun save(echo: Echo)

}
