package net.averak.cap.usecase

import net.averak.cap.AbstractSpec
import net.averak.cap.domain.repository.IEchoRepository
import net.averak.cap.domain.repository.IProjectRepository
import org.spockframework.spring.SpringBean

class AbstractUsecase_UT extends AbstractSpec {

    @SpringBean
    IEchoRepository echoRepository = Mock()

    @SpringBean
    IProjectRepository projectRepository = Mock()

}