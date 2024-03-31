import io.tnboot.gradle.build.ConfigurePlugin
import io.tnboot.gradle.build.PublishPlugin.Companion.configurePublish
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
	alias(libs.plugins.kotlin.jvm)
	alias(libs.plugins.kotlin.dokka)
	alias(libs.plugins.kotlin.kapt)
	alias(libs.plugins.kotlin.spring)
	alias(libs.plugins.spring.boot)
	alias(libs.plugins.spring.deps)
	`maven-publish`
	jacoco
	`jacoco-report-aggregation`
}

dependencies {
	dokkaHtmlPlugin(libs.kotlin.dokka.versioning)
}

allprojects {
	group = "io.tnboot"
	version = System.getenv("VERSION") ?: "UNVERSIONED"

	repositories {
		mavenCentral()
	}

	layout.buildDirectory.set(
		rootProject.mkdir("build/_" + project.relativePath(project.path).replace(Regex("[^a-z0-9]"), "_")),
	)

	// region Plugin Configurations
	apply(plugin = "maven-publish")

	// telenor-boot-dependencies is a special project that uses the
	// `javaPlatform` plugin, which is incompatible with most other
	// plugins. We skip applying the other plugins by returning early.
	if (name == "telenor-boot-dependencies") return@allprojects

	apply(plugin = "org.jetbrains.kotlin.jvm")
	apply(plugin = "org.jetbrains.dokka")
	apply(plugin = "org.jetbrains.kotlin.kapt")
	apply(plugin = "org.jetbrains.kotlin.plugin.spring")
	apply(plugin = "org.springframework.boot")
	apply(plugin = "io.spring.dependency-management")
	apply(plugin = "jacoco")
	apply(plugin = "jacoco-report-aggregation")
	pluginManager.apply(ConfigurePlugin::class.java)
	// endregion

	configurePublish {
		from(components["kotlin"])
	}

	dependencies {
		testImplementation(kotlin("test"))
	}

	// region Kotlin Compiler Configurations
	kotlin.jvmToolchain(21)

	tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
		kotlinOptions {
			freeCompilerArgs += "-Xjsr305=strict"
			freeCompilerArgs += "-Xjvm-default=all"
			jvmTarget = "21"
		}
	}
	// endregion

	// region Packaging Configurations
	tasks.withType<Jar> {
		manifest {
			attributes["Implementation-Title"] = project.name
			attributes["Implementation-Version"] = project.version
		}
	}

	tasks.withType<BootJar> {
		enabled = false
	}

	// endregion
}
