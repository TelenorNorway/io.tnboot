package io.tnboot.gradle.plugin

import io.tnboot.gradle.addExtension
import io.tnboot.gradle.github.GitHubActionsExtension
import io.tnboot.gradle.github.GitHubCredentialsImpl
import io.tnboot.gradle.github.GitHubExtension
import io.tnboot.gradle.github.GitHubExtensionImpl
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.plugins.PublishingPlugin

class GitHubPlugin : Plugin<Project> {
	override fun apply(target: Project) {
		val github = GitHubExtensionImpl(target.repositories, GitHubCredentialsImpl(target))

		target.repositories.addExtension<GitHubExtension>("github", github)

		target.plugins.withType(PublishingPlugin::class.java) { _ ->
			target.extensions.getByType(PublishingExtension::class.java).repositories
				.addExtension<GitHubActionsExtension>("github", github)
		}
	}
}
