package net.averak.cap

import groovy.sql.Sql
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional

@Transactional
abstract class AbstractDatabaseSpec extends AbstractSpec {

    @Autowired
    Sql sql

}
