package net.averak.cap.domain.repository

import net.averak.cap.domain.model.Project
import net.averak.cap.domain.primitive.common.ID

interface IProjectRepository {

    fun findAll(): List<Project>

    fun findById(id: ID): Project?

}
