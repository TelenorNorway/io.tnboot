package io.tnboot.gradle

import io.spring.gradle.dependencymanagement.DependencyManagementPlugin
import io.tnboot.gradle.environment.Environment
import io.tnboot.gradle.plugin.GitHubPlugin
import io.tnboot.gradle.plugin.JavaForkOptionsDotEnvPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.plugin.KotlinPluginWrapper
import org.springframework.boot.gradle.plugin.SpringBootPlugin

/**
 * A helper plugin to easier take in use the Telenor Boot framework,
 * built on top of Spring Boot.
 */
@Suppress("unused")
class TelenorBootPlugin : Plugin<Project> {
	/**
	 * Apply the plugin to the given project.
	 */
	override fun apply(target: Project) {
		// Initialize environment variables.
		Environment[target]

		// Load plugins.
		loadPlugins(target)
	}

	private companion object {
		private val loadPlugins = plugins(
			// Inject default plugins
			plugin<KotlinPluginWrapper>(), // Kotlin (JVM)
			plugin("org.jetbrains.kotlin.kapt"), // Kotlin Annotation Processor
			plugin("org.jetbrains.kotlin.plugin.spring"), // Kotlin Spring (All-Open for Spring)
			plugin<DependencyManagementPlugin>(), // Spring Dependency Management
			plugin<SpringBootPlugin>(), // Spring Boot

			// Conditional Plugins
			plugin("org.jetbrains.kotlin.plugin.jpa", projectContainsArtifactsWithSubstringPredicate("jpa")),

			// Telenor Boot Plugins
			plugin<JavaForkOptionsDotEnvPlugin>(),
			plugin<GitHubPlugin>(),
		)
	}
}
