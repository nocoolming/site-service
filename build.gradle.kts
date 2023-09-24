plugins {
    id("org.springframework.boot") version "3.1.4"
    id("io.spring.dependency-management") version "1.1.0"
    id("java")
    application
}

group = "com.ming.site"
version = "1.0-SNAPSHOT"

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-test")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-data-rest")
    implementation("org.postgresql:postgresql:42.6.0")

    implementation ("com.zaxxer:HikariCP:5.0.1")

    implementation("ch.qos.logback:logback-core:1.3.5")
    implementation("ch.qos.logback:logback-classic:1.3.5")
    implementation("org.slf4j:slf4j-api:2.0.4")

    implementation("com.google.guava:guava:32.1.2-jre")
    implementation("xyz.downgoon:snowflake:1.0.0")

    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

application {
    mainClass.set("com.ming.site.Application")
}

tasks.test {
    useJUnitPlatform()
}

tasks.register("welcome"){
    doLast {
        println("Welcome to Gradle")
    }
}
