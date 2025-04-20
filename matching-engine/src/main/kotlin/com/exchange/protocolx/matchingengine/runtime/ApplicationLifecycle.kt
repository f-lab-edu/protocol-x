package com.exchange.protocolx.matchingengine.runtime

import org.slf4j.LoggerFactory
import java.util.concurrent.CountDownLatch

class ApplicationLifecycle {
    private val logger = LoggerFactory.getLogger(ApplicationLifecycle::class.java)
    private val latch = CountDownLatch(1)
    
    init {
        // 종료 신호 처리를 위한 셧다운 훅 등록
        Runtime.getRuntime().addShutdownHook(Thread {
            logger.info("Shutdown signal received, initiating graceful shutdown...")
            shutdown()
        })
    }
    
    // 애플리케이션이 종료 신호를 받을 때까지 실행 상태를 유지
    fun waitForShutdown() {
        try {
            logger.info("Application running.")
            latch.await()
        } catch (e: InterruptedException) {
            logger.error("Application was interrupted", e)
        }
        
        logger.info("Application shutdown complete")
    }
    
    // 애플리케이션을 정상적으로 종료
    fun shutdown() {
        logger.info("Shutting down application...")
        latch.countDown()
    }
} 