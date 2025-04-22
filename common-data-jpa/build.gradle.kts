plugins {
    kotlin("plugin.jpa")
    id("java-test-fixtures")
}

dependencies {
    api("org.springframework.boot:spring-boot-starter-data-jpa")
    
    // Test
    testFixturesImplementation("org.springframework.boot:spring-boot-testcontainers")
    testFixturesImplementation("org.testcontainers:mysql")
    testFixturesImplementation("org.testcontainers:junit-jupiter")
    testFixturesImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.getByName<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
    enabled = false
}

tasks.getByName<Jar>("jar") {
    enabled = true
}
