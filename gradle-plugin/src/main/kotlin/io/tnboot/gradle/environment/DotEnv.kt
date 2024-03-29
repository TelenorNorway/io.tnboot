package io.tnboot.gradle.environment

import io.github.cdimascio.dotenv.dotenv
import org.gradle.api.Project

/**
 * A DotEnv object that contains the contents of the `.env` files.
 *
 * This object is responsible for loading the `.env` files and caching
 * the contents, so that the files are only read once.
 */
internal object DotEnv {
	private var initialized = false
	private lateinit var cached: Map<String, String>

	fun load(project: Project) = synchronized(this) {
		if (!initialized) {
			initialized = true

			cached = dotenv {
				directory = project.rootProject.projectDir.absolutePath
				ignoreIfMissing = true
				ignoreIfMalformed = true
			}.entries().associate { it.key to it.value }
		}

		cached
	}
}
