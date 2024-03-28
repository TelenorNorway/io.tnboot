package io.tnboot.gradle.build

import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.api.publish.plugins.PublishingPlugin
import java.net.URI

// todo(James Bradlee): Replace this with io.tnboot's GitHub plugin
//                      once it's been implemented.
private fun PublishingExtension.githubActionsRepository() {
	if ((System.getenv("GITHUB_ACTIONS") ?: "false") != "true") return
	System.getenv("GITHUB_REPOSITORY")
		?: throw Exception("\$GITHUB_REPOSITORY is missing from the environment variables! Is this really CI?")
	repositories.maven { r ->
		r.name = "GPRActions"
		r.url = URI.create("https://maven.pkg.github.com/${System.getenv("GITHUB_REPOSITORY")}")
		r.credentials { cred ->
			cred.username = System.getenv("GITHUB_ACTOR")
				?: throw Exception("Missing environment variable 'GITHUB_ACTOR'")
			cred.password = System.getenv("GITHUB_TOKEN")
				?: throw Exception("Missing environment variable 'GITHUB_TOKEN'")
		}
	}
}

fun Project.mavenPublish(block: MavenPublication.() -> Unit) {
	plugins.withType(PublishingPlugin::class.java) { _ ->
		extensions.getByType(PublishingExtension::class.java).apply {
			githubActionsRepository()
			publications.create("maven", MavenPublication::class.java) {
				it.apply(block)
			}
		}
	}
}
