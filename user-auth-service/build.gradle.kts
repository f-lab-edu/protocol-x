plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
    kotlin("plugin.jpa")
}

dependencies {
    implementation(project(":common"))
    
    // Spring Web
    implementation("org.springframework.boot:spring-boot-starter-web")
    
    // Spring Data JPA
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    
    // Spring Security
    implementation("org.springframework.boot:spring-boot-starter-security")
    
    // JWT
    implementation("com.auth0:java-jwt:4.5.0")
    
    // DB
    runtimeOnly("com.mysql:mysql-connector-j")
} 
