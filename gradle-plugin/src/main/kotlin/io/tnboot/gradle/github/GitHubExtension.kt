package io.tnboot.gradle.github

import org.gradle.api.artifacts.dsl.RepositoryHandler

/**
 * A [RepositoryHandler] extension for adding GitHub Packages
 * repositories to a project.
 */
interface GitHubExtension {
	/**
	 * Adds a GitHub repository to the project.
	 *
	 * The [repos] parameter will default to `"*"` if none are provided.
	 *
	 * @param owner the repository owner
	 * @param repos the repository names
	 */
	fun use(owner: String, vararg repos: String)

	/**
	 * Adds the Telenor Boot GitHub repository to the project.
	 */
	fun tnboot() = use("telenornorway", "io.tnboot")
}
