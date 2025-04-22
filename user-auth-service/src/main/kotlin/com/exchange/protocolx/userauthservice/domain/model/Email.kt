package com.exchange.protocolx.userauthservice.domain.model

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.util.regex.Pattern

@Embeddable
class Email(
    @Column(name = "email", nullable = false, unique = true)
    val value: String
) {
    init {
        validate(value)
    }

    companion object {
        private val EMAIL_PATTERN = Pattern.compile(
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
            "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
        )

        private fun validate(email: String) {
            require(email.isNotBlank()) { "이메일은 필수 값입니다." }
            require(EMAIL_PATTERN.matcher(email).matches()) { "유효한 이메일 형식이 아닙니다: $email" }
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Email
        return value == other.value
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }

    override fun toString(): String {
        return value
    }
}
