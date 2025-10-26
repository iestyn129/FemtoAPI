plugins {
	kotlin("jvm") version "2.2.20"
}

group = "dev.iestyn129.femtoapi"
version = "0.1.2"

repositories {
	mavenCentral()
}

dependencies {
	implementation(kotlin("reflect:2.2.20"))
	implementation(files("lib/TynLog.jar"))
}

kotlin {
	jvmToolchain(8)
}
