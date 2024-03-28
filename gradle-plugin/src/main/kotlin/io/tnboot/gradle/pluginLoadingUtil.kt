package io.tnboot.gradle

import org.gradle.api.Project
import org.slf4j.LoggerFactory
import java.util.stream.Stream
import kotlin.reflect.KClass

private val log = LoggerFactory.getLogger(PluginContainer::class.java)

/**
 * A container for a plugin that should be applied to a project.
 */
internal sealed interface PluginContainer {
	/**
	 * A check to see whether the contained plugin should be applied to
	 * the given project. If the plugin should be applied, the method
	 * should return true, otherwise false.
	 */
	fun shouldBeApplied(project: Project): Boolean

	/**
	 * Apply the contained plugin to the given project.
	 */
	fun apply(project: Project)
}

/**
 * A [PluginContainer] implementation that supports applying a Gradle
 * plugin to a project by its plugin ID.
 */
private class PluginContainerWithPluginId(
	private val pluginId: String,
	private val predicate: (Project) -> Boolean,
) : PluginContainer {
	override fun shouldBeApplied(project: Project): Boolean {
		log.debug("Checking if {} should be applied to {}", pluginId, project)
		return predicate(project)
	}

	override fun apply(project: Project) {
		log.debug("Applying {} to {}", pluginId, project)
		project.pluginManager.apply(pluginId)
	}
}

/**
 * A [PluginContainer] implementation that supports applying a Gradle
 * plugin to a project by its plugin class.
 */
private class PluginContainerWithPluginClass(
	private val pluginClass: KClass<*>,
	private val predicate: (Project) -> Boolean,
) : PluginContainer {
	override fun shouldBeApplied(project: Project): Boolean {
		log.debug("Checking if {} should be applied to {}", pluginClass.simpleName, project)
		return predicate(project)
	}

	override fun apply(project: Project) {
		log.debug("Applying {} to {}", pluginClass.simpleName, project)
		project.pluginManager.apply(pluginClass.java)
	}
}

/**
 * Create a [PluginContainer] for a plugin with the given ID.
 */
internal fun plugin(id: String, predicate: (Project) -> Boolean = { true }): PluginContainer =
	PluginContainerWithPluginId(id, predicate)

/**
 * Create a [PluginContainer] for a plugin with the given class.
 */
internal fun plugin(klass: KClass<*>, predicate: (Project) -> Boolean = { true }): PluginContainer =
	PluginContainerWithPluginClass(klass, predicate)

/**
 * Create a [PluginContainer] for a plugin with the given class.
 */
internal inline fun <reified T : Any> plugin(noinline predicate: (Project) -> Boolean = { true }): PluginContainer =
	plugin(T::class, predicate)

/**
 * A utility function that takes a list of plugins and returns a
 * function that applies the plugins to a given project.
 */
internal fun plugins(vararg plugins: PluginContainer): (Project) -> Unit {
	return { project ->
		Stream.of(*plugins)
			.filter { it.shouldBeApplied(project) }
			.forEach { it.apply(project) }
	}
}
