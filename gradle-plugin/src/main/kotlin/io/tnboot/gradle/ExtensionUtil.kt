package io.tnboot.gradle

import org.gradle.api.plugins.ExtensionAware

internal inline fun <reified T : Any> Any.addExtension(name: String, value: T) {
	(this as ExtensionAware).extensions.add(T::class.java, name, value)
}
