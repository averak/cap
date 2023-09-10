package net.averak.cap.domain.service

import net.averak.cap.core.utils.PortUtils
import net.averak.cap.domain.model.Project
import net.averak.cap.domain.primitive.project.HostPort
import net.averak.cap.domain.primitive.project.ProjectName
import net.averak.cap.domain.repository.IProjectRepository
import org.springframework.stereotype.Service

@Service
open class ProjectService(
    private val projectRepository: IProjectRepository,
) {

    open fun isNameAlreadyUsed(name: ProjectName): Boolean {
        return this.projectRepository.existsByName(name)
    }

    open fun allocateHostPort(project: Project): HostPort? {
        val usedHostPorts = this.projectRepository.findAll().mapNotNull(Project::hostPort)

        // 現時点でプロセスが割り当てられていない && 別プロジェクトが予約していないポートを検索する
        (HostPort.MIN..HostPort.MAX)
            .map(::HostPort)
            .filter { !usedHostPorts.contains(it) }
            .forEach {
                if (PortUtils.isUnused(it.value)) {
                    project.allocateHostPort(it)
                    return@allocateHostPort it
                }
            }

        return null
    }
}
