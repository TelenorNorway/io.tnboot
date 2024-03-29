package io.tnboot.gradle.build

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.publish.maven.MavenPublication
import org.slf4j.LoggerFactory

/**
 * Internal plugin to configure maven publications on all projects,
 * given that the project does not disable publishing with
 * [Extension.disable] and that the project has no children (unless
 * explicitly allowed with [Extension.publishEvenWithChildren]).
 */
class PublishPlugin : Plugin<Project> {
	companion object {
		private val log = LoggerFactory.getLogger(PublishPlugin::class.java)

		fun Project.configurePublish(block: MavenPublication.() -> Unit) {
			extensions.getByType(Extension::class.java).configure(block)
		}
	}

	open class Extension {
		var enabled = true
		var evenWithChildren = false

		fun disable() {
			enabled = false
		}

		fun publishEvenWithChildren() {
			evenWithChildren = true
		}

		internal val configurations: MutableList<MavenPublication.() -> Unit> = mutableListOf()

		fun configure(block: MavenPublication.() -> Unit) {
			configurations.add(block)
		}
	}

	override fun apply(target: Project) {
		target.extensions.add(Extension::class.java, "publish", Extension())
		target.afterEvaluate {
			target.extensions.getByType(Extension::class.java).let { extension ->
				if (!extension.enabled || (target.childProjects.isNotEmpty() && !extension.evenWithChildren)) return@afterEvaluate
				log.debug("Enabling Maven Publications for project {}", target.name)
				target.mavenPublish {
					extension.configurations.forEach {
						log.debug("Applied Maven Publication configuration {} for project {}", it, target.name)
						apply(it)
					}
				}
				log.debug("Applied for {}", target.name)
			}
		}
	}
}
