package com.exchange.protocolx.ordermanagementservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(basePackages = ["com.exchange.protocolx.common", "com.exchange.protocolx.ordermanagementservice"])
class OrderManagementServiceApplication

fun main(args: Array<String>) {
    runApplication<OrderManagementServiceApplication>(*args)
} 