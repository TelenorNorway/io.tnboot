package io.tnboot.gradle.plugin

import io.tnboot.gradle.environment.Environment
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * A plugin that automatically sets the version of the project to
 * the value of the `VERSION` environment variable, if it exists.
 * If the `VERSION` environment variable does not exist, the version
 * is set to `"UNVERSIONED"`.
 */
class AutoVersionPlugin : Plugin<Project> {
	override fun apply(target: Project) {
		target.beforeEvaluate {
			target.version = Environment[target]["VERSION"] ?: "UNVERSIONED"
		}
	}
}
