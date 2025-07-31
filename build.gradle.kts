import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    // Apply the java-library plugin for API and implementation separation.
    `java-library`
    `maven-publish`
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.mockito:mockito-core:5.12.0")
    testImplementation("org.hamcrest:hamcrest:2.2")
}


testing {
    suites {
        // Configure the built-in test suite
        val test by getting(JvmTestSuite::class) {
            // Use JUnit Jupiter test framework
            useJUnitJupiter("5.10.1")
        }
    }
}

tasks.test {
    testLogging {
        showCauses = true
        showStackTraces = true
        showExceptions = true
        info {
            events(TestLogEvent.PASSED, TestLogEvent.FAILED, TestLogEvent.SKIPPED)
        }
        debug {
            events(TestLogEvent.STARTED, TestLogEvent.PASSED, TestLogEvent.FAILED, TestLogEvent.SKIPPED, TestLogEvent.STANDARD_OUT, TestLogEvent.STANDARD_ERROR)
        }
    }
}

// Apply a specific Java toolchain to ease working on different environments.
java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "net.biesemeyer"
            artifactId = "cleaner"
            version = "1.0.0"

            from(components["java"])

            pom {
                name = "Cleaner"
                description = "Utilities for Cleaner"
                licenses {
                    license {
                        name = "Apache-2.0"
                        url = "http://www.apache.org/licenses/LICENSE-2.0.txt"
                    }
                }
                developers {
                    developer {
                        name = "Rye Biesemeyer"
                        email = "rye@biesemeyer.net"
                    }
                }
                scm {
                    connection = "scm:git:git://github.com:biesemeyer/cleaner.git"
                    developerConnection = "scm:git:git@github.com:biesemeyer/cleaner.git"
                    url = "https://github.com/biesemeyer/cleaner"
                }
            }
        }
    }
}
