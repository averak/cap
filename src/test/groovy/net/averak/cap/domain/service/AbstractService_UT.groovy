package net.averak.cap.domain.service

import net.averak.cap.AbstractSpec
import net.averak.cap.domain.repository.IProjectRepository
import org.spockframework.spring.SpringBean

abstract class AbstractService_UT extends AbstractSpec {

    @SpringBean
    IProjectRepository projectRepository = Mock()

}