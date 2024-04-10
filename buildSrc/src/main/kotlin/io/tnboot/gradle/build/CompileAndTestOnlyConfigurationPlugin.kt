package io.tnboot.gradle.build

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.SourceSetContainer

class CompileAndTestOnlyConfigurationPlugin : Plugin<Project> {
	override fun apply(target: Project) {
		val compileAndTestOnly = target.configurations.create("compileAndTestOnly") {
			it.isCanBeConsumed = true
			it.isCanBeResolved = true
		}

		val sourceSets = target.extensions.getByType(SourceSetContainer::class.java)

		val mainSourceSet = sourceSets.getByName("main")
		mainSourceSet.compileClasspath += compileAndTestOnly

		val testSourceSet = sourceSets.getByName("test")
		testSourceSet.compileClasspath += compileAndTestOnly
		testSourceSet.runtimeClasspath += compileAndTestOnly
	}
}
