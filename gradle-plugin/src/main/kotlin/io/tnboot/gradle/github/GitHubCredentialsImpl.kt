package io.tnboot.gradle.github

import io.tnboot.gradle.environment.Environment
import org.gradle.api.Project

internal class GitHubCredentialsImpl(
	private val project: Project,
) : GitHubCredentials {

	private val username: String? get() = Environment[project]["GPR_ACTOR"] ?: Environment[project]["GITHUB_ACTOR"]
	private val password: String? get() = Environment[project]["GPR_TOKEN"] ?: Environment[project]["GITHUB_TOKEN"]

	private var usernameAndPassword: Pair<String, String>? = null

	private fun getUsernameAndPassword(): Pair<String, String> = synchronized(this) {
		Pair(
			username ?: throw IllegalStateException("`GPR_ACTOR` environment variable is not set"),
			password ?: throw IllegalStateException("`GPR_TOKEN` environment variable is not set"),
		).also { usernameAndPassword = it }
	}

	override fun get(): Pair<String, String> {
		return usernameAndPassword ?: getUsernameAndPassword()
	}
}
