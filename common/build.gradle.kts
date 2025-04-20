plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
}

tasks.getByName<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
    enabled = false
}

tasks.getByName<Jar>("jar") {
    enabled = true
}

dependencies {
    api("org.springframework.boot:spring-boot-starter-validation")
    
    // Logging
    api("io.github.microutils:kotlin-logging-jvm:3.0.5")
} 