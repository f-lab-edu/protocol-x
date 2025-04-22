package com.exchange.protocolx.userauthservice.domain.repository

import com.exchange.protocolx.userauthservice.domain.model.User
import com.exchange.protocolx.userauthservice.domain.model.UserStatus
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

interface UserRepository : JpaRepository<User, Long> {
    
    @Query("SELECT u FROM User u WHERE u.email.value = :email")
    fun findByEmail(@Param("email") email: String): User?
    
    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM User u WHERE u.email.value = :email")
    fun existsByEmail(@Param("email") email: String): Boolean
    
    fun findAllByStatus(status: UserStatus): List<User>
}
