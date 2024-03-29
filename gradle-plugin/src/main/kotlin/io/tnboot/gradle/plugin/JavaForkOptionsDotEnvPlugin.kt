package io.tnboot.gradle.plugin

import io.tnboot.gradle.environment.Environment
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.process.JavaForkOptions

/**
 * A plugin that configures the [JavaForkOptions] from the `.env`
 * file.
 */
class JavaForkOptionsDotEnvPlugin : Plugin<Project> {
	override fun apply(target: Project) {
		target.tasks.filterIsInstance<JavaForkOptions>()
			.forEach(Environment[target]::applyTo)
	}
}
