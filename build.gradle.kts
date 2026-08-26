plugins {
	kotlin("jvm") version "2.2.20"
	id("com.vanniktech.maven.publish") version "0.37.0"
}

group = "dev.iestyn129.femtoapi"
version = "0.1.4"

layout.buildDirectory = file("out")

repositories {
	mavenCentral()
}

dependencies {
	implementation("dev.iestyn129.tynlog:tynlog:0.1.3")
}

kotlin {
	jvmToolchain(8)
}

mavenPublishing {
	publishToMavenCentral()
	signAllPublications()

	coordinates(artifactId = "femtoapi")

	pom {
		name.set("FemtoAPI")
		description.set("A super tiny and super simple HTTP server and API framework designed for only basic needs of me (iestyn129).")
		url.set("https://github.com/iestyn129/FemtoAPI")

		licenses {
			license {
				name.set("GNU General Public License v3 or later")
				url.set("https://www.gnu.org/licenses/gpl-3.0.html")
			}
		}

		developers {
			developer {
				id.set("iestyn129")
				name.set("iestyn129")
			}
		}

		scm {
			connection.set("scm:git:git://github.com/iestyn129/FemtoAPI.git")
			developerConnection.set("scm:git:ssh://github.com/iestyn129/FemtoAPI.git")
			url.set("https://github.com/iestyn129/FemtoAPI")
		}
	}
}
