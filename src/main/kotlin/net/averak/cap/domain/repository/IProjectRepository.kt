package net.averak.cap.domain.repository

import net.averak.cap.domain.model.Project

interface IProjectRepository {

    fun findAll(): List<Project>

}
