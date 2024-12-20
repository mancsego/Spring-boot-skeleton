plugins {
	id("org.springframework.boot") version "3.4.0"
	id("io.spring.dependency-management") version "1.1.6"
	id("org.jetbrains.kotlinx.kover") version "0.8.3"
	kotlin("jvm") version "2.1.0"
	kotlin("plugin.spring") version "2.1.0"
}

group = "com.mancsego"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(23)
	}
}

kover {
	reports {
		total {
			binary {
				file = file(".coverage/report.ic")
			}
		}
	}
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	developmentOnly("org.springframework.boot:spring-boot-devtools:3.4.1")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation(kotlin("test"))

	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.bootRun {
	jvmArgs = listOf("-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005")
}

tasks.withType<Test> {
	useJUnitPlatform()
	finalizedBy("koverBinaryReport")
}
