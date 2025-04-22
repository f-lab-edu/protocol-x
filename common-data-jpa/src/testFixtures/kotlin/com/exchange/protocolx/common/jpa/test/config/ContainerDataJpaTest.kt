package com.exchange.protocolx.common.jpa.test.config

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import java.lang.annotation.Inherited
import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.TYPE

/**
 * `@DataJpaTest` 와 MySQL Testcontainers 설정을 결합한 커스텀 어노테이션.
 * Testcontainers 설정은 별도의 인터페이스 구현을 통해 관리됩니다 (@ServiceConnection 사용).
 */
@Target(TYPE, AnnotationTarget.CLASS)
@Retention(RUNTIME)
@Inherited
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
annotation class ContainerDataJpaTest
