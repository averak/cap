package net.averak.cap

import groovy.sql.Sql
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.transaction.annotation.Transactional

import javax.sql.DataSource

@Transactional
abstract class AbstractDatabaseSpec extends AbstractSpec {

    @Autowired
    Sql sql

    @TestConfiguration
    static class TestConfig {

        @Bean
        Sql sql(final DataSource dataSource) {
            return new Sql(dataSource)
        }

    }

}
