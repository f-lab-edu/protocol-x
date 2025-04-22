plugins {
    kotlin("plugin.jpa")
}

dependencies {
    implementation(project(":common"))
    implementation(project(":common-data-jpa"))
    
    // Spring Web
    implementation("org.springframework.boot:spring-boot-starter-web")
    
    // Spring Security
    implementation("org.springframework.boot:spring-boot-starter-security")
    
    // JWT
    implementation("com.auth0:java-jwt")
    
    // DB
    runtimeOnly("com.mysql:mysql-connector-j")
    
    // Test
    testImplementation(testFixtures(project(":common-data-jpa")))
}

tasks.getByName<Jar>("jar") {
    enabled = false
} 
