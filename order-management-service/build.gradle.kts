plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
    kotlin("plugin.jpa")
}

dependencies {
    implementation(project(":common"))
    
    // Web
    implementation("org.springframework.boot:spring-boot-starter-web")
    
    // JPA
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    
    // DB
    runtimeOnly("com.mysql:mysql-connector-j")
} 
