plugins {
	alias(libs.plugins.tnn)
	alias(libs.plugins.kotlin.jvm)
	alias(libs.plugins.kotlin.dokka)
	`maven-publish`
	jacoco
	`jacoco-report-aggregation`
}

allprojects {
	apply(plugin = "sh.tnn")
	apply(plugin = "org.jetbrains.kotlin.jvm")
	apply(plugin = "org.jetbrains.dokka")
	apply(plugin = "maven-publish")
	apply(plugin = "jacoco")
	apply(plugin = "jacoco-report-aggregation")

	group = "io.tnboot"

	repositories {
		mavenCentral()
	}

	dependencies {
		testImplementation(kotlin("test"))
	}

	tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
		kotlinOptions {
			freeCompilerArgs += "-Xjsr305=strict"
			freeCompilerArgs += "-Xjvm-default=all"
			jvmTarget = "21"
		}
	}

	tasks.test {
		useJUnitPlatform()
	}
}
