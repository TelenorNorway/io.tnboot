package io.tnboot.gradle.github

import org.gradle.api.artifacts.dsl.RepositoryHandler
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.plugins.PublishingPlugin

/**
 * A [RepositoryHandler] extension for adding the current GitHub
 * Packages repository to the [PublishingExtension], which is provided
 * by the [PublishingPlugin].
 */
interface GitHubActionsExtension {
	/**
	 * Adds the current GitHub Packages repository to the project.
	 *
	 * This method will return when the `GITHUB_ACTIONS` environment
	 * variable is not equal to `"true"`.
	 *
	 * And will throw an exception if any of the following environment
	 * variables are missing:
	 * - `GITHUB_REPOSITORY`
	 * - `GPR_ACTOR` (or `GITHUB_ACTOR`)
	 * - `GPR_TOKEN` (or `GITHUB_TOKEN`)
	 *
	 * It should be noted that the `GITHUB_ACTOR` and `GITHUB_TOKEN`
	 * environment variables are used to authenticate with the GitHub
	 * Packages repository. However, these are only available for
	 * GitHub Actions workflows for convenience.
	 *
	 * Human consumers of this plugin SHOULD define these as `GPR_ACTOR`
	 * and `GPR_TOKEN` respectively.
	 */
	fun actions()
}
