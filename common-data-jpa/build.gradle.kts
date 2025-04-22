plugins {
    kotlin("plugin.jpa")
    id("java-test-fixtures")
}

dependencies {
    api("org.springframework.boot:spring-boot-starter-data-jpa")

    testFixturesImplementation("org.springframework.boot:spring-boot-starter-test")
    testFixturesApi("org.springframework.boot:spring-boot-testcontainers")
    testFixturesApi("org.testcontainers:mysql")
    testFixturesApi("org.testcontainers:junit-jupiter")
}

tasks.getByName<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
    enabled = false
}

tasks.getByName<Jar>("jar") {
    enabled = true
}
