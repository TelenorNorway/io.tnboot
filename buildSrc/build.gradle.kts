plugins {
	kotlin("jvm") version "1.9.23"
}

repositories {
	mavenCentral()
}

dependencies {
	val jacksonVersion = "2.15.4"
	implementation("com.fasterxml.jackson.core:jackson-core:$jacksonVersion")
	implementation("com.fasterxml.jackson.core:jackson-databind:$jacksonVersion")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonVersion")
	implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:$jacksonVersion")
}

kotlin.jvmToolchain(21)

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		freeCompilerArgs += "-Xjvm-default=all"
		jvmTarget = "21"
	}
}
