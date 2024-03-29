package io.tnboot.gradle.plugin

import io.tnboot.gradle.environment.Environment
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.process.JavaForkOptions
import org.slf4j.LoggerFactory

/**
 * A plugin to inject an OpenTelemetry agent when the
 * `OTEL_JAVA_AGENT` environment variable is set to a valid absolute
 * path pointing to the java agent jar file.
 */
class InjectOpenTelemetryAgentPlugin : Plugin<Project> {
	private companion object {
		private val log = LoggerFactory.getLogger(InjectOpenTelemetryAgentPlugin::class.java)
	}

	override fun apply(target: Project) {
		inject(target)

		// future idea:
		// Maybe add a task to fetch the latest version of the agent
		// and append it to the `OTEL_JAVA_AGENT` environment variable
		// if it is not set in the `.env` file.
	}

	private fun inject(project: Project) {
		val path = Environment[project]["OTEL_JAVA_AGENT"]

		if (path == null) {
			log.debug("OTEL_JAVA_AGENT is not set")
			return
		}

		val file = project.rootProject.file(path)

		if (!file.exists()) {
			log.warn("OTEL_JAVA_AGENT file does not exist: {}", file)
			return
		}

		log.debug("Injecting OpenTelemetry agent: {}", file)

		project.tasks.all {
			if (it !is JavaForkOptions) return@all
			it.jvmArgs("-XX:+EnableDynamicAgentLoading", "-javaagent:${file.absolutePath}")
		}
	}
}
