package com.exchange.protocolx.userauthservice.domain.model

import com.exchange.protocolx.common.jpa.AggregateRootEntity
import com.exchange.protocolx.userauthservice.domain.event.UserCreated
import com.exchange.protocolx.userauthservice.domain.event.UserRoleChanged
import com.exchange.protocolx.userauthservice.domain.event.UserStatusChanged
import jakarta.persistence.*

@Entity
@Table(name = "users")
class User private constructor(
    
    @Embedded
    private val email: Email,
    
    @Embedded
    private var password: Password,
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private var role: Role = Role.USER,
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private var status: UserStatus = UserStatus.INACTIVE
) : AggregateRootEntity<User>() {

    companion object {
        fun create(
            password: String,
            email: String,
            role: Role = Role.USER
        ): User {
            val user = User(
                password = Password(password),
                email = Email(email),
                role = role,
                status = UserStatus.INACTIVE
            )
            
            return user
        }
    }
    
    /**
     * 사용자 생성 후 ID가 할당되면 호출하여 생성 이벤트를 발행
     */
    @PostPersist
    private fun publishCreatedEvent() {
        registerEvent(UserCreated(
            userId = id ?: throw IllegalStateException("User ID cannot be null after persist"),
            email = email.value
        ))
    }

    fun getEmail(): String = email.value

    fun getStatus(): UserStatus = status

    fun getRole(): Role = role

    fun activate(): Boolean {
        if (status == UserStatus.ACTIVE) {
            return false
        }
        
        val oldStatus = status
        status = UserStatus.ACTIVE
        
        registerEvent(UserStatusChanged(
            userId = id ?: throw IllegalStateException("User ID cannot be null"),
            oldStatus = oldStatus,
            newStatus = status
        ))
        
        return true
    }

    fun suspend(): Boolean {
        if (status == UserStatus.SUSPENDED) {
            return false
        }
        
        val oldStatus = status
        status = UserStatus.SUSPENDED
        
        registerEvent(UserStatusChanged(
            userId = id ?: throw IllegalStateException("User ID cannot be null"),
            oldStatus = oldStatus,
            newStatus = status
        ))
        
        return true
    }
    
    fun inactivate(): Boolean {
        if (status == UserStatus.INACTIVE) {
            return false
        }
        
        val oldStatus = status
        status = UserStatus.INACTIVE
        
        registerEvent(UserStatusChanged(
            userId = id ?: throw IllegalStateException("User ID cannot be null"),
            oldStatus = oldStatus,
            newStatus = status
        ))
        
        return true
    }
    
    fun changePassword(rawPassword: String) {
        password = Password(rawPassword)
    }
    
    fun checkPassword(rawPassword: String): Boolean {
        return password.matches(rawPassword)
    }
    
    fun updateRole(newRole: Role): Boolean {
        if (role == newRole) {
            return false
        }
        
        val oldRole = role
        role = newRole
        
        registerEvent(UserRoleChanged(
            userId = id ?: throw IllegalStateException("User ID cannot be null"),
            oldRole = oldRole,
            newRole = newRole
        ))
        
        return true
    }
    
    fun isAdmin(): Boolean = role == Role.ADMIN
}
