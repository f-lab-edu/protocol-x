package com.exchange.protocolx.userauthservice.domain.repository

import com.exchange.protocolx.common.jpa.test.config.ContainerDataJpaTest
import com.exchange.protocolx.common.jpa.test.config.MySqlTestContainers
import com.exchange.protocolx.userauthservice.domain.model.User
import com.exchange.protocolx.userauthservice.domain.model.UserStatus
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

@ContainerDataJpaTest
class UserRepositoryTest @Autowired constructor(
    private val userRepository: UserRepository
) : MySqlTestContainers {

    @Test
    @DisplayName("사용자를 저장하고 ID로 조회할 수 있어야 한다")
    fun `save and find user by id`() {
        // given
        val user = User.create(
            password = "password1234",
            email = "test@example.com"
        )

        // when
        val savedUser = userRepository.saveAndFlush(user)

        val foundUser = userRepository.findById(savedUser.id!!).orElse(null)

        // then
        assertNotNull(foundUser)
        assertEquals(savedUser.id, foundUser.id)
        assertEquals("test@example.com", foundUser.getEmail())
    }

    @Test
    @DisplayName("사용자를 이메일로 조회할 수 있어야 한다")
    fun `find user by email`() {
        // given
        val user = User.create(
            password = "password1234",
            email = "test@example.com"
        )

        // when
        userRepository.saveAndFlush(user)
        val foundUser = userRepository.findByEmail("test@example.com")

        // then
        assertNotNull(foundUser)
        assertEquals("test@example.com", foundUser?.getEmail())
    }

    @Test
    @DisplayName("이메일 존재 여부를 확인할 수 있어야 한다")
    fun `check if email exists`() {
        // given
        val user = User.create(
            password = "password1234",
            email = "test@example.com"
        )

        // when
        userRepository.saveAndFlush(user)
        val exists = userRepository.existsByEmail("test@example.com")
        val notExists = userRepository.existsByEmail("nonexistent@example.com")

        // then
        assertTrue(exists)
        assertFalse(notExists)
    }

    @Test
    @DisplayName("특정 상태의 사용자들을 조회할 수 있어야 한다")
    fun `find users by status`() {
        // given
        val user1 = User.create(password = "password1234", email = "user1@example.com")
        val user2 = User.create(password = "password1234", email = "user2@example.com")
        val user3 = User.create(password = "password1234", email = "user3@example.com")

        // when
        userRepository.saveAllAndFlush(listOf(user1, user2, user3))

        // ID 할당 후 상태 변경
        val fetchedUser1 = userRepository.findByEmail("user1@example.com")!!
        fetchedUser1.activate()
        userRepository.saveAndFlush(fetchedUser1)

        val activeUsers = userRepository.findAllByStatus(UserStatus.ACTIVE)
        val inactiveUsers = userRepository.findAllByStatus(UserStatus.INACTIVE)
        val suspendedUsers = userRepository.findAllByStatus(UserStatus.SUSPENDED)

        // then
        assertEquals(1, activeUsers.size)
        assertEquals(2, inactiveUsers.size)
        assertEquals(0, suspendedUsers.size)

        activeUsers.forEach { assertEquals(UserStatus.ACTIVE, it.getStatus()) }
        inactiveUsers.forEach { assertEquals(UserStatus.INACTIVE, it.getStatus()) }
    }
}
