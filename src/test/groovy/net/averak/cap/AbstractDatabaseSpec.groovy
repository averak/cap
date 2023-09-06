package net.averak.cap

import groovy.sql.Sql
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional

import javax.sql.DataSource

@Transactional
abstract class AbstractDatabaseSpec extends AbstractSpec {

    protected static Sql sql

    @Autowired
    void setSql(DataSource dataSource) {
        sql = new Sql(dataSource)
    }

}
