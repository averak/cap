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

    open fun allocateHostPort(project: Project) {
        // TODO: #10 テストを書くこと
        // 現時点でプロセスが割り当てられていないポートは、別プロジェクトに予約されている可能性があるので、割り当て手順がやや複雑になる
        val containersUsedHostPorts = this.projectRepository.findAll().mapNotNull(Project::hostPort)

        var cursor = HostPort.MIN
        val pageSize = 10
        while (cursor + pageSize < HostPort.MAX) {
            val unusedPort = PortUtils.findUnusedPorts(cursor, pageSize)
                .map(::HostPort)
                .firstOrNull { containersUsedHostPorts.contains(it).not() }
            if (unusedPort != null) {
                project.allocateHostPort(unusedPort)
                break
            }

            cursor += pageSize
        }
    }

}
