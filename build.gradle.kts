plugins {
	`maven-publish`
}

allprojects {
	group = "io.tnboot"
	version = System.getenv("VERSION") ?: "UNVERSIONED"

	repositories {
		mavenCentral()
	}

	apply(plugin = "maven-publish")

	// telenor-boot-dependencies is a special project that uses the
	// `javaPlatform` plugin, which is incompatible with most other
	// plugins. We skip applying the other plugins by returning early.
	if (name == "telenor-boot-dependencies") return@allprojects

	// future: add other plugins
}
