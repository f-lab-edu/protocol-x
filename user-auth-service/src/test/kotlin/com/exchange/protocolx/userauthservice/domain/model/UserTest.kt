package com.exchange.protocolx.userauthservice.domain.model

import com.exchange.protocolx.userauthservice.domain.event.UserRoleChanged
import com.exchange.protocolx.userauthservice.domain.event.UserStatusChanged
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.test.util.ReflectionTestUtils

class UserTest {
    
    @Test
    fun `유저 생성 시 기본 상태는 INACTIVE여야 한다`() {
        // when
        val user = User.create(
            password = "password1234",
            email = "test@example.com"
        )
        
        // then
        assertEquals(UserStatus.INACTIVE, user.getStatus())
        assertEquals(Role.USER, user.getRole())
        assertNotNull(user.createdAt)
    }
    
    @Test
    fun `유효하지 않은 이메일로 유저를 생성하면 예외가 발생해야 한다`() {
        // when & then
        assertThrows<IllegalArgumentException> {
            User.create(
                password = "password1234",
                email = "invalid-email"
            )
        }
    }
    
    @Test
    fun `비밀번호가 최소 길이보다 짧으면 예외가 발생해야 한다`() {
        // when & then
        assertThrows<IllegalArgumentException> {
            User.create(
                password = "123", // 4자 미만
                email = "test@example.com"
            )
        }
    }
    
    @Test
    fun `activate 메서드 호출 시 상태가 ACTIVE로 변경되고 이벤트가 발생해야 한다`() {
        // given
        val user = createPersistedUser()
        
        // when
        val result = user.activate()
        
        // then
        assertTrue(result)
        assertEquals(UserStatus.ACTIVE, user.getStatus())
        
        // 이벤트 확인
        val events = user.getEvents()
        assertTrue(events.isNotEmpty())
        
        val event = events.find { it is UserStatusChanged } as UserStatusChanged?
        assertNotNull(event)
        assertEquals(UserStatus.INACTIVE, event!!.oldStatus)
        assertEquals(UserStatus.ACTIVE, event.newStatus)
    }
    
    @Test
    fun `suspend 메서드 호출 시 상태가 SUSPENDED로 변경되고 이벤트가 발생해야 한다`() {
        // given
        val user = createPersistedUser()
        
        // when
        val result = user.suspend()
        
        // then
        assertTrue(result)
        assertEquals(UserStatus.SUSPENDED, user.getStatus())
        
        // 이벤트 확인
        val events = user.getEvents()
        assertTrue(events.isNotEmpty())
        
        val event = events.find { it is UserStatusChanged } as UserStatusChanged?
        assertNotNull(event)
        assertEquals(UserStatus.INACTIVE, event!!.oldStatus)
        assertEquals(UserStatus.SUSPENDED, event.newStatus)
    }
    
    @Test
    fun `updateRole 메서드 호출 시 role이 변경되고 이벤트가 발생해야 한다`() {
        // given
        val user = createPersistedUser()
        
        // when
        val result = user.updateRole(Role.ADMIN)
        
        // then
        assertTrue(result)
        assertEquals(Role.ADMIN, user.getRole())
        assertTrue(user.isAdmin())
        
        // 이벤트 확인
        val events = user.getEvents()
        assertTrue(events.isNotEmpty())
        
        val event = events.find { it is UserRoleChanged } as UserRoleChanged?
        assertNotNull(event)
        assertEquals(Role.USER, event!!.oldRole)
        assertEquals(Role.ADMIN, event.newRole)
    }
    
    @Test
    fun `checkPassword 메서드는 올바른 비밀번호를 검증해야 한다`() {
        // given
        val user = User.create(
            password = "password1234",
            email = "test@example.com"
        )
        
        // when & then
        assertTrue(user.checkPassword("password1234"))
        assertFalse(user.checkPassword("wrongpassword"))
    }
    
    @Test
    fun `changePassword 메서드는 비밀번호를 변경해야 한다`() {
        // given
        val user = User.create(
            password = "password1234",
            email = "test@example.com"
        )
        
        // when
        user.changePassword("newpassword1234")
        
        // then
        assertTrue(user.checkPassword("newpassword1234"))
        assertFalse(user.checkPassword("password1234"))
    }

    private fun createPersistedUser(): User {
        val user = User.create(
            password = "password1234",
            email = "test@example.com"
        )
        ReflectionTestUtils.setField(user, "id", 1L)
        return user
    }
}
