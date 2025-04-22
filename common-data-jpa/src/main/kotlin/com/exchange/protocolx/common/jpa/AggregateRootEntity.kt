package com.exchange.protocolx.common.jpa

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.domain.AbstractAggregateRoot
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

/**
 * 애그리게이트 루트를 위한 기본 클래스
 * 모든 애그리게이트 루트는 이 클래스를 상속
 * 
 * 사용법:
 * ```
 * @Entity
 * class MyAggregate(
 *     // 기타 필드들
 * ) : AggregateRootEntity<MyAggregate>() {
 *     // 비즈니스 메서드
 * }
 * ```
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class AggregateRootEntity<T : AggregateRootEntity<T>> : AbstractAggregateRoot<T>(), BaseEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null

    @CreatedDate
    @Column(nullable = false, updatable = false)
    override var createdAt: LocalDateTime = LocalDateTime.now()
        protected set

    @LastModifiedDate
    @Column(nullable = false)
    override var updatedAt: LocalDateTime = LocalDateTime.now()
        protected set

    fun getId(): Long? = id

    fun getEvents(): List<Any> {
        return domainEvents().toList()
    }
}
