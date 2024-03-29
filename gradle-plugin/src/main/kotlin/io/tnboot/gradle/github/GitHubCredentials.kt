package io.tnboot.gradle.github

interface GitHubCredentials {
	/**
	 * Get the GitHub credentials.
	 * @return A [Pair] of the GitHub actor and token.
	 */
	fun get(): Pair<String, String>
}
