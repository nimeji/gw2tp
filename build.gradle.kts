import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	kotlin("plugin.jpa") version "1.6.10"
	id("org.springframework.boot") version "2.6.3"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	id("com.palantir.git-version") version "0.13.0"
	id("org.jetbrains.kotlin.plugin.allopen") version "1.5.21"
	kotlin("jvm") version "1.6.10"
	kotlin("plugin.spring") version "1.6.10"
}

group = "com.nimeji"
val gitVersion: groovy.lang.Closure<String> by extra
version = gitVersion()
java.sourceCompatibility = JavaVersion.VERSION_11

allOpen {
	annotations("javax.persistence.Entity", "javax.persistence.MappedSuperclass", "javax.persistence.Embedabble")
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.keycloak:keycloak-spring-boot-starter:17.0.0")
	implementation("io.ktor:ktor-client-core:1.6.7")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-mustache")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("io.github.resilience4j:resilience4j-ratelimiter:1.7.1")
	implementation("io.ktor:ktor-client-okhttp:1.6.7")
	implementation("io.ktor:ktor-client-jackson:1.6.7")
    implementation("io.github.microutils:kotlin-logging:2.1.21")
	runtimeOnly("mysql:mysql-connector-java")
    runtimeOnly("com.h2database:h2")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

springBoot {
	buildInfo()
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
