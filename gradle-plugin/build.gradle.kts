@file:Suppress("UnstableApiUsage")

plugins {
	alias(libs.plugins.publish.gradle)
	`java-gradle-plugin`
}

dependencies {
	implementation(libs.dotenv)
	implementation(libs.kotlin.plugin.jvm)
	implementation(libs.kotlin.plugin.kapt)
	implementation(libs.kotlin.plugin.jpa)
	implementation(libs.kotlin.plugin.spring)
	implementation(libs.spring.plugin.boot)
	implementation(libs.spring.plugin.deps)
}

gradlePlugin {
	website = "https://github.com/telenornorway/io.tnboot"
	vcsUrl = "https://github.com/telenornorway/io.tnboot"
	plugins {
		create("tnboot") {
			id = "io.tnboot"
			implementationClass = "io.tnboot.gradle.TelenorBootPlugin"
			displayName = "Telenor Boot Plugin"
			description = "A helper plugin to easier take in use the TnBoot framework, built on top of Spring Boot"
			tags = listOf("telenor", "tnboot", "boot", "spring", "vault", "dotenv", "github")
		}
	}
}

tasks.withType<Jar> {
	manifest {
		attributes["Implementation-Title"] = "Telenor Boot Plugin"
		attributes["Implementation-Version"] = project.version
	}
}
