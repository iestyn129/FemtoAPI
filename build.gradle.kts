plugins {
	kotlin("jvm") version "2.2.20"
}

group = "dev.iestyn129.femtoapi"
version = "0.1.3"

layout.buildDirectory = file("out")

repositories {
	mavenCentral()
}

dependencies {
	implementation(kotlin("reflect:${kotlin.coreLibrariesVersion}"))
	implementation(files("lib/TynLog.jar"))
}

tasks.jar {
	archiveBaseName = project.name
	archiveVersion = "v${project.version}"
}

kotlin {
	jvmToolchain(8)
}
