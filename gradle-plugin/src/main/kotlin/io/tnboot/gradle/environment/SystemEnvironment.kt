package io.tnboot.gradle.environment

/**
 * A singleton object that caches the system environment variables.
 */
internal object SystemEnvironment {
	private var initialized = false
	private lateinit var cached: Map<String, String>

	fun load() = synchronized(this) {
		if (!initialized) {
			initialized = true

			cached = System.getenv()
		}

		cached
	}

	operator fun get(key: String) = load()[key]
}
