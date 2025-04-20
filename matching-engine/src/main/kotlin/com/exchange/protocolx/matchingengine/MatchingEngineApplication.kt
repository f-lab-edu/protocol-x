package com.exchange.protocolx.matchingengine

import com.exchange.protocolx.matchingengine.runtime.ApplicationLifecycle
import com.exchange.protocolx.matchingengine.service.MatchingEngineService
import org.slf4j.LoggerFactory

// 임시
fun main() {
    val matchingEngineService = MatchingEngineService()
    
    val lifecycle = ApplicationLifecycle()
    lifecycle.waitForShutdown()
} 
