package io.tnboot.gradle.github

import org.gradle.api.artifacts.dsl.RepositoryHandler
import java.net.URI

private val INVALID_CHARS_REGEX = Regex("[^A-Za-z0-9_.]+")

/**
 * Adds a GitHub Maven repository to the repository handler.
 *
 * @param owner The owner of the repository.
 * @param repository The name of the repository.
 * @param actor The actor to authenticate as.
 * @param token The token to authenticate with.
 */
internal fun RepositoryHandler.github(repository: String, credentials: GitHubCredentials) {
	this.maven { r ->
		r.name = "GitHubPackages $repository".replace(INVALID_CHARS_REGEX, "_")
		r.url = URI.create("https://maven.pkg.github.com/$repository")
		r.credentials {
			val (actor, token) = credentials.get()
			it.username = actor
			it.password = token
		}
	}
}
