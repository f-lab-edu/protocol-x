plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
    kotlin("plugin.jpa")
}

dependencies {
    implementation(project(":common"))
    implementation(project(":common-data-jpa"))
    
    // Web
    implementation("org.springframework.boot:spring-boot-starter-web")
    
    // DB
    runtimeOnly("com.mysql:mysql-connector-j")
} 
