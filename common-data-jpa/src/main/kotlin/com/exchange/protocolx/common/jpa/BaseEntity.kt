package com.exchange.protocolx.common.jpa

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

interface BaseEntity {
    val createdAt: LocalDateTime
    val updatedAt: LocalDateTime
}

/**
 * ID가 특수한 형태로 지정되거나 ID가 필요 없는 엔티티에서 사용
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseTimeEntity : BaseEntity {
    @CreatedDate
    @Column(nullable = false, updatable = false)
    override var createdAt: LocalDateTime = LocalDateTime.now()
        protected set

    @LastModifiedDate
    @Column(nullable = false)
    override var updatedAt: LocalDateTime = LocalDateTime.now()
        protected set
}
