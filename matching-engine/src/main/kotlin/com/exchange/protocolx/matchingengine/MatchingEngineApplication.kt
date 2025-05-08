package com.exchange.protocolx.matchingengine

import com.exchange.protocolx.matchingengine.runtime.ApplicationLifecycle

// 임시
fun main() {
    val lifecycle = ApplicationLifecycle()
    lifecycle.waitForShutdown()
} 
