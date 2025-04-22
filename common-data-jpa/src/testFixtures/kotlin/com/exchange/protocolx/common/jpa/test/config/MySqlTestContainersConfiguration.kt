package com.exchange.protocolx.common.jpa.test.config

import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.context.annotation.Bean
import org.testcontainers.containers.MySQLContainer

@TestConfiguration(proxyBeanMethods = false)
class MySqlTestContainersConfiguration {

    @Bean
    @ServiceConnection
    fun mysqlContainer(): MySQLContainer<*> {
        return MySQLContainer<Nothing>("mysql:8.0").apply {
            withDatabaseName("testdb")
            withUsername("testuser")
            withPassword("testpass")
            withReuse(true)
        }
    }
} 
