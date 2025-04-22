package com.exchange.protocolx.userauthservice.domain.model

enum class Role(val description: String) {
    USER("일반 사용자"),
    ADMIN("관리자");
    
    companion object {
        fun fromString(value: String): Role {
            return valueOf(value.uppercase())
        }
    }
}
