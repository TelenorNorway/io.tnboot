import io.tnboot.gradle.build.DependencyGroups
import io.tnboot.gradle.build.mavenPublish
import io.tnboot.gradle.build.notation
import io.tnboot.gradle.build.publishedProjects

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
		publishedProjects.forEach { add("api", it.notation) }
	}
}

mavenPublish {
	from(components["javaPlatform"])
	pom {
		properties.put("tnboot.version", version as String)
		properties.putAll(bom.versionProperties)
	}
}
