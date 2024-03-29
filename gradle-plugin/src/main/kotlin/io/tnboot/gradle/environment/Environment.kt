package io.tnboot.gradle.environment

import org.gradle.api.Project
import org.gradle.process.ProcessForkOptions

class Environment(
	private val inner: Map<String, String>,
) : Map<String, String> {
	companion object {
		private var initialized = false
		private lateinit var cached: Environment

		operator fun get(project: Project) = synchronized(this) {
			if (!initialized) {
				cached = Environment(
					mutableMapOf<String, String>()
						.apply { putAll(SystemEnvironment.load()) }
						.apply { putAll(DotEnv.load(project)) },
				)
			}

			cached
		}

		operator fun get(key: String) = synchronized(this) {
			if (!initialized) {
				throw Exception("Environment not initialized")
			}

			cached[key]
		}
	}

	override val entries get() = inner.entries
	override val keys get() = inner.keys
	override val size get() = inner.size
	override val values get() = inner.values
	override fun isEmpty() = inner.isEmpty()
	override fun get(key: String) = inner[key]
	override fun containsValue(value: String) = inner.containsValue(value)
	override fun containsKey(key: String) = inner.containsKey(key)

	/**
	 * Applies the environment variables to the [ProcessForkOptions].
	 */
	fun applyTo(options: ProcessForkOptions) {
		for ((key, value) in this) {
			options.environment(key, value)
		}
	}
}
