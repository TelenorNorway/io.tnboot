package io.tnboot.gradle.plugin

import io.spring.gradle.dependencymanagement.DependencyManagementPlugin
import io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension
import io.tnboot.gradle.TelenorBootPlugin.Companion.VERSION
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * A plugin that adds the Telenor Boot dependencies BOM to the
 * project.
 */
class TelenorBootDependenciesPlugin : Plugin<Project> {
	private companion object {
		private val COORDINATES = "io.tnboot:telenor-boot-dependencies:$VERSION"
	}

	override fun apply(target: Project) {
		target.plugins.withType(DependencyManagementPlugin::class.java) { _ ->
			target.extensions
				.getByType(DependencyManagementExtension::class.java)
				.imports { it.mavenBom(COORDINATES) }
		}
	}
}
