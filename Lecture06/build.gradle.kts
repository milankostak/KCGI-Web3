import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.5.5"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    war
    kotlin("jvm") version "1.5.31"
    kotlin("plugin.spring") version "1.5.31"
}

group = "edu.kcg.web3"
version = "0.0.1"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
    mavenCentral()
}

val springBootVersion = "2.5.5"
val kotlinVersion = "1.5.31"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web:$springBootVersion")
    implementation("org.springframework.boot:spring-boot-starter-mustache:$springBootVersion")
    implementation("org.springframework.boot:spring-boot-starter-security:$springBootVersion")
    providedRuntime("org.springframework.boot:spring-boot-starter-tomcat:$springBootVersion")

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion")
    implementation("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.12.5")
    implementation("org.bouncycastle:bcprov-jdk15on:1.69") // password hashing

    implementation("com.graphql-java:graphql-java:17.3")
    implementation("com.graphql-java:graphql-java-spring-boot-starter-webmvc:2.0")
    implementation("com.graphql-java-kickstart:graphiql-spring-boot-starter:11.1.0")

    testImplementation("org.springframework.boot:spring-boot-starter-test:$springBootVersion")
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
