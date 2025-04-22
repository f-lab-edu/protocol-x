package com.exchange.protocolx.userauthservice.domain.event

import com.exchange.protocolx.userauthservice.domain.model.Role
import com.exchange.protocolx.userauthservice.domain.model.UserStatus
import java.time.LocalDateTime

sealed interface UserEvent {
    val userId: Long
    val timestamp: LocalDateTime
}

data class UserCreated(
    override val userId: Long,
    val email: String,
    override val timestamp: LocalDateTime = LocalDateTime.now()
) : UserEvent

data class UserStatusChanged(
    override val userId: Long,
    val oldStatus: UserStatus,
    val newStatus: UserStatus,
    override val timestamp: LocalDateTime = LocalDateTime.now()
) : UserEvent

data class UserRoleChanged(
    override val userId: Long,
    val oldRole: Role,
    val newRole: Role,
    override val timestamp: LocalDateTime = LocalDateTime.now()
) : UserEvent 
