package io.tnboot.gradle.build

import org.gradle.api.Plugin
import org.gradle.api.Project

class ConfigurePlugin : Plugin<Project> {
	override fun apply(target: Project) {
		target.pluginManager.apply(PublishPlugin::class.java)
	}
}
