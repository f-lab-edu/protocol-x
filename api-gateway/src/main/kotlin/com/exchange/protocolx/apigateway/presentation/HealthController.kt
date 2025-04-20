package com.exchange.protocolx.apigateway.presentation

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import java.time.LocalDateTime

@RestController
class HealthController {
    
    @GetMapping("/health")
    fun health(): Mono<Map<String, Any>> {
        return Mono.just(mapOf(
            "service" to "api-gateway",
            "status" to "UP",
            "timestamp" to LocalDateTime.now().toString()
        ))
    }
} 