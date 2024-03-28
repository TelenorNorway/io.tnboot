rootProject.name = "tnboot"

// @formatter:off

dependencyResolutionManagement {
	versionCatalogs {
		create("libs") {
			version("kotlin", "1.9.23")
			version("dokka", "1.9.20")

			// Gradle Plugin
			plugin("gradle-plugin-publish", "com.gradle.plugin-publish").version("1.2.1")

			// Kotlin
			plugin("kotlin-jvm", "org.jetbrains.kotlin.jvm").versionRef("kotlin")
			plugin("kotlin-dokka", "org.jetbrains.dokka").versionRef("dokka")

			// In-house
			plugin("tnn", "sh.tnn").version("0.2.0")
		}
	}
}

// @formatter:on
