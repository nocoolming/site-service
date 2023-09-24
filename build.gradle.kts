plugins {
    id("org.springframework.boot") version "3.1.4"
    id("io.spring.dependency-management") version "1.1.0"
    id("java")
    application
}

group = "com.ming.site"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-test")

    // the GOAT ORM
    implementation ("org.hibernate.orm:hibernate-core:6.3.0.Final")

    // Hibernate Validator
    implementation ("org.hibernate.validator:hibernate-validator:8.0.0.Final")
    implementation ("org.glassfish:jakarta.el:4.0.2")
    implementation ("org.hibernate:hibernate-hikaricp:6.3.1.Final")
    implementation ("com.zaxxer:HikariCP:5.0.1")


    implementation("org.postgresql:postgresql:42.6.0")
//    runtimeOnly ("com.h2database:h2")
//    implementation("org.xerial:sqlite-jdbc:3.36.0.3")
//    implementation("com.github.gwenn:sqlite-dialect:0.1.4")
    implementation("com.google.guava:guava:32.1.2-jre")
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
