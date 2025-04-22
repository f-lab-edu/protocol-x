package com.exchange.protocolx.userauthservice.domain.model

enum class UserStatus(val description: String) {
    ACTIVE("활성화된 상태"),
    INACTIVE("비활성화된 상태 (이메일 인증 등이 되지 않은 경우)"),
    SUSPENDED("정지된 상태 (관리자에 의해 일시 정지된 경우)");
    
    companion object {
        fun fromString(value: String): UserStatus {
            return valueOf(value.uppercase())
        }
    }
}
