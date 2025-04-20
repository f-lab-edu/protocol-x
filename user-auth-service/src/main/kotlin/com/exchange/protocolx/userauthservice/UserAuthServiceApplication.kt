package com.exchange.protocolx.userauthservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(basePackages = ["com.exchange.protocolx.common", "com.exchange.protocolx.userauthservice"])
class UserAuthServiceApplication

fun main(args: Array<String>) {
    runApplication<UserAuthServiceApplication>(*args)
} 