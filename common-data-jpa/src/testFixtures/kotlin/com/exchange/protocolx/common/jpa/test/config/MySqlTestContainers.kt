package com.exchange.protocolx.common.jpa.test.config

import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.utility.DockerImageName

/**
 * `@ServiceConnection`을 사용하여 MySQL Testcontainers 설정을 공통화하는 인터페이스
 */
@Testcontainers
interface MySqlTestContainers {

    companion object {
        @Container
        @ServiceConnection
        val mysqlContainer = MySQLContainer<Nothing>(DockerImageName.parse("mysql:8.0")).apply {
            withDatabaseName("testdb")
            withUsername("testuser")
            withPassword("testpass")
        }
    }
} 
