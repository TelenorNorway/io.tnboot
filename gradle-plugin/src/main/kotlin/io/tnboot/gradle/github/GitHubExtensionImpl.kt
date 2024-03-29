package io.tnboot.gradle.github

import io.tnboot.gradle.environment.SystemEnvironment
import org.gradle.api.artifacts.dsl.RepositoryHandler

internal class GitHubExtensionImpl(
	private val repositories: RepositoryHandler,
	private val credentials: GitHubCredentials,
) : GitHubExtension, GitHubActionsExtension {
	override fun actions() {
		// Here we don't want `.env` files to be used, so we use
		// [SystemEnvironment] directly.

		if (SystemEnvironment["GITHUB_ACTIONS"] != "true") return

		val repository = SystemEnvironment["GITHUB_REPOSITORY"]
			?: throw Exception("\$GITHUB_REPOSITORY is missing from the environment variables! Is this really CI?")

		repositories.github(repository, credentials)
	}

	override fun use(owner: String, vararg repos: String) {
		repos
			.ifEmpty { arrayOf("*") }
			.forEach { repositories.github("$owner/$it", credentials) }
	}
}
