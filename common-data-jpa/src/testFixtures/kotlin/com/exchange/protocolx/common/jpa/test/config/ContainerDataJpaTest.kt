package com.exchange.protocolx.common.jpa.test.config

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import

/**
 * MySQL Testcontainers 사용하여 `@DataJpaTest` 테스트
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@DataJpaTest
@Import(MySqlTestContainersConfiguration::class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
annotation class ContainerDataJpaTest
