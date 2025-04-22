package com.exchange.protocolx.userauthservice.domain.model

import jakarta.persistence.Column
import jakarta.persistence.Embeddable

@Embeddable
class Password(
    @Column(name = "password", nullable = false)
    val value: String
) {
    init {
        validate(value)
    }

    companion object {
        private const val MIN_LENGTH = 4

        private fun validate(password: String) {
            require(password.isNotBlank()) { "비밀번호는 빈 값일 수 없습니다" }
            require(password.length >= MIN_LENGTH) { "비밀번호는 최소 ${MIN_LENGTH}자 이상이어야 합니다" }
        }
    }

    /**
     * 실제 해시된 비밀번호 값은 외부에 노출하지 않음
     */
    override fun toString(): String {
        return "******"
    }

    // TODO: Security Bcrypt 적용
    fun matches(rawPassword: String): Boolean {
        return value == rawPassword
    }
}
