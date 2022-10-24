import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.7.5"
	id("io.spring.dependency-management") version "1.0.14.RELEASE"
	war
	kotlin("jvm") version "1.7.20"
	kotlin("plugin.spring") version "1.7.20"
}

group = "edu.kcg.web3"
version = "0.0.1"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

val springBootVersion = "2.7.5"
val kotlinVersion = "1.7.20"

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web:$springBootVersion")
	implementation("org.springframework.boot:spring-boot-starter-mustache:$springBootVersion")
	providedRuntime("org.springframework.boot:spring-boot-starter-tomcat:$springBootVersion")

	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion")
	implementation("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")

	testImplementation("org.springframework.boot:spring-boot-starter-test:$springBootVersion")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
