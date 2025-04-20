package com.exchange.protocolx.ordermanagementservice.presentation

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
class HealthController {
    
    @GetMapping("/health")
    fun health(): Map<String, Any> {
        return mapOf(
            "service" to "order-management-service",
            "status" to "UP",
            "timestamp" to LocalDateTime.now().toString()
        )
    }
} 