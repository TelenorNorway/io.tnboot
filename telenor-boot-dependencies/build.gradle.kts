import io.tnboot.gradle.build.DependencyGroups
import io.tnboot.gradle.build.PublishPlugin
import io.tnboot.gradle.build.mavenPublish

plugins {
	`java-platform`
}

javaPlatform.allowDependencies()

val bom = DependencyGroups(
	rootProject.file("gradle/dependencies.yaml"),
	libs.versions,
)

dependencies {
	constraints {
		bom.dependencies.forEach { add("api", it) }

		rootProject.allprojects.filter {
			it != project && it.plugins.hasPlugin(PublishPlugin::class.java) &&
				it.extensions.getByType(PublishPlugin.Extension::class.java).isEnabled
		}.forEach {
			add("api", it)
		}
	}
}

mavenPublish {
	from(components["javaPlatform"])
	pom {
		properties.put("tnboot.version", version as String)
		properties.putAll(bom.versionProperties)
	}
}
