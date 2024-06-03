rootProject.name = "tnboot"

include(
	":telenor-boot-dependencies",
	":gradle-plugin",
)

include(
	":projects:logging:colorful",
	":projects:logging:logging-model",
)

include(
	":util:util-homedir",
)
