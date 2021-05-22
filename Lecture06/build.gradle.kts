import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.4.5"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    war
    kotlin("jvm") version "1.4.32"
    kotlin("plugin.spring") version "1.4.32"
}

group = "edu.kcg.web3"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
    mavenCentral()
}

val springBootVersion = "2.4.5"
val kotlinVersion = "1.4.32"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web:$springBootVersion")
    implementation("org.springframework.boot:spring-boot-starter-mustache:$springBootVersion")
    implementation("org.springframework.boot:spring-boot-starter-security:$springBootVersion")
    providedRuntime("org.springframework.boot:spring-boot-starter-tomcat:$springBootVersion")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.12.3")

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion")
    implementation("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")

    testImplementation("org.springframework.boot:spring-boot-starter-test:$springBootVersion")
    testImplementation("org.springframework.security:spring-security-test:5.4.6")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
