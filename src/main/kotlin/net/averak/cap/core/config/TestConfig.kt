package net.averak.cap.core.config

import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy
import javax.sql.DataSource

@Profile("test")
@Configuration
open class TestConfig {

    @Bean
    open fun flywayMigrationStrategy(): FlywayMigrationStrategy {
        return FlywayMigrationStrategy { flyway ->
            flyway.clean()
            flyway.migrate()
        }
    }

    @Bean
    open fun dataSource(dataSourceProperties: DataSourceProperties): DataSource {
        // アプリケーション/テストで実行されるトランザクションを1つにまとめる
        return TransactionAwareDataSourceProxy(
            DataSourceBuilder.create()
                .username(dataSourceProperties.username)
                .password(dataSourceProperties.password)
                .url(dataSourceProperties.url)
                .driverClassName(dataSourceProperties.driverClassName)
                .build()
        )
    }

}
