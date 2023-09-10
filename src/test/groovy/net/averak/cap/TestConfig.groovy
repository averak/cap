package net.averak.cap

import groovy.sql.Sql
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy

import javax.sql.DataSource

@TestConfiguration
class TestConfig {

    @Bean
    FlywayMigrationStrategy flywayMigrationStrategy() {
        // テスト開始時にDBをリセットする
        return {
            it.clean()
            it.migrate()
        }
    }

    @Bean
    DataSource dataSource(final DataSourceProperties dataSourceProperties) {
        // アプリケーション/テストで実行されるトランザクションを1つにまとめる
        return new TransactionAwareDataSourceProxy( //
            DataSourceBuilder.create()
                .username(dataSourceProperties.getUsername())
                .password(dataSourceProperties.getPassword())
                .url(dataSourceProperties.getUrl())
                .driverClassName(dataSourceProperties.getDriverClassName())
                .build()
        )
    }

    @Bean
    Sql sql(final DataSource dataSource) {
        return new Sql(dataSource)
    }

}
