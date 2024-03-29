package io.tnboot.gradle

import org.gradle.api.Project

internal fun containsSubstringInArtifacts(project: Project, substring: String): Boolean {
	return project.configurations.any { configuration ->
		configuration.allDependencies.any { dependency ->
			if (dependency == null) {
				false
			} else {
				(dependency.group?.let { "$it:" } + dependency.name).contains(substring)
			}
		}
	}
}

internal fun projectContainsArtifactsWithSubstringPredicate(substring: String): (Project) -> Boolean {
	return { project -> containsSubstringInArtifacts(project, substring) }
}

internal fun Project.explicitDependenciesFromAllConfigurations() = this
	.configurations.flatMap { configuration ->
		configuration.allDependencies.mapNotNull { dependency ->
			"${dependency.group ?: return@mapNotNull null}:${dependency.name}"
		}
	}.toSet()
