package io.tnboot.gradle.build

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.KotlinFeature
import com.fasterxml.jackson.module.kotlin.kotlinModule
import com.fasterxml.jackson.module.kotlin.readValue
import org.gradle.api.internal.catalog.AbstractExternalDependencyFactory.VersionFactory
import org.gradle.api.provider.Provider
import java.io.File
import kotlin.reflect.full.memberFunctions
import kotlin.reflect.jvm.javaMethod

private val mapper = ObjectMapper(YAMLFactory())
	.registerModule(kotlinModule { enable(KotlinFeature.StrictNullChecks) })

/**
 * A class that reads a YAML file containing dependency groups and
 * provides a list of dependencies and a map of version properties.
 */
class DependencyGroups(
	file: File,
	versionFactory: VersionFactory,
) {
	internal data class DependencyGroup(
		/**
		 * The title of the dependency group. May be used to visualize
		 * the group in some user interface.
		 */
		val title: String,

		/**
		 * A link to the repository where the code for the dependencies
		 * in this group can be found.
		 */
		val repository: String,

		/**
		 * A link to the website of the organization that maintains the
		 * dependencies in this group.
		 */
		val website: String,

		/**
		 * The group ID of the dependencies in this group.
		 */
		val group: String,

		/**
		 * The version reference of the dependencies in this group.
		 */
		val version: String,

		/**
		 * The list of artifacts in this group.
		 */
		val artifacts: List<String>,
	)

	@Suppress("MemberVisibilityCanBePrivate")
	val dependencies: List<String>

	@Suppress("MemberVisibilityCanBePrivate")
	val versionProperties: Map<String, String>

	init {
		/**
		 * Hey Gradle... What's up with all the private and protected
		 * methods!?! Just give us a public API to get the version of a
		 * provider in the version catalog. It's not that hard!
		 *
		 * This is a workaround to get the version of a provider in the
		 * version catalog. It uses reflection to get the method that
		 * gets the version of a provider and then invokes it to get the
		 * version of a provider.
		 *
		 * See [VersionFactory.getVersion].
		 */
		val versionOf = versionFactory::class.memberFunctions.first { it.name == "getVersion" }
			.let { it.javaMethod!! }
			.also { it.isAccessible = true }
			.let {
				{ name: String ->
					@Suppress("UNCHECKED_CAST")
					(it.invoke(versionFactory, name) as Provider<String>).get()
				}
			}

		val groups = mapper.readValue<List<DependencyGroup>>(file)

		dependencies = groups.flatMap { group ->
			group.artifacts.map { artifact ->
				"${group.group}:$artifact:\${${group.version}.version}"
			}
		}

		versionProperties = groups.associate { group ->
			"${group.version}.version" to versionOf(group.version)
		}
	}
}
