package net.averak.cap.usecase

import net.averak.cap.AbstractSpec
import net.averak.cap.domain.repository.IEchoRepository
import net.averak.cap.domain.repository.IMaintenanceRepository
import net.averak.cap.domain.repository.IProjectRepository
import org.spockframework.spring.SpringBean

abstract class AbstractUsecase_UT extends AbstractSpec {

    @SpringBean
    IEchoRepository echoRepository = Mock()

    @SpringBean
    IMaintenanceRepository maintenanceRepository = Mock()

    @SpringBean
    IProjectRepository projectRepository = Mock()

}