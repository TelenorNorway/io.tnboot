package io.tnboot.gradle.plugin

import io.tnboot.gradle.excludeArtifactsFromAllConfigurations
import io.tnboot.gradle.explicitDependenciesFromAllConfigurations
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.slf4j.LoggerFactory

/**
 * This plugin automatically excludes artifacts from the project,
 * unless they are explicitly included.
 */
class AutoExcludeArtifactsPlugin : Plugin<Project> {
	private companion object {
		private val log = LoggerFactory.getLogger(AutoExcludeArtifactsPlugin::class.java)

		private val excludes = listOf(
			"commons-logging" to "commons-logging",
		)
	}

	override fun apply(target: Project) {
		target.afterEvaluate(::afterEvaluate)
	}

	private fun afterEvaluate(project: Project) {
		val allExplicitDependencies = project.explicitDependenciesFromAllConfigurations()

		log.debug(
			"Explicit dependencies: {}",
			allExplicitDependencies.joinToString("") { "\n    $it" },
		)

		val excludes = excludes.filterNot { (group, name) ->
			allExplicitDependencies.contains("$group:$name")
		}

		log.debug(
			"Excluding dependencies because they are not explicitly depended upon: {}",
			excludes.joinToString("") { "\n    ${it.first}:${it.second}" },
		)

		project.excludeArtifactsFromAllConfigurations(excludes)
	}
}
