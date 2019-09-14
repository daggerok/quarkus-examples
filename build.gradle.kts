import org.gradle.api.tasks.testing.logging.TestLogEvent.*

buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("io.quarkus:quarkus-gradle-plugin:0.21.2")
    }
}

plugins {
    idea
    java
    kotlin("jvm") version "1.3.50" apply false
    id("com.github.ben-manes.versions") version "0.25.0" apply false
    id("com.avast.gradle.docker-compose") version "0.9.4" apply false
    // https://github.com/quarkusio/quarkus/issues/3552#issuecomment-524225607
    // id("io.quarkus") version "0.21.2" apply false
}

allprojects {
    version = "0.0.3-SNAPSHOT"
    group = "com.github.daggerok.quarkus"

    repositories {
        mavenCentral()
        //// not needed, all dependencies should be in central
        //maven(url = uri("https://repository.jboss.org/nexus/content/groups/public"))
    }
}

subprojects {
    apply<JavaPlugin>()

    java {
        sourceCompatibility = JavaVersion.VERSION_1_8
        // targetCompatibility = JavaVersion.VERSION_1_8
    }

    apply<io.quarkus.gradle.QuarkusPlugin>()

    dependencies {
        implementation(enforcedPlatform("io.quarkus:quarkus-bom:0.21.2"))
        implementation(enforcedPlatform("io.vavr:vavr:0.10.2"))
    }

    tasks {
        test {
            testLogging {
                showExceptions = true
                showStandardStreams = true
                events(PASSED, SKIPPED, FAILED)
            }
            useJUnitPlatform()
            // systemProperty("quarkus.test.profile", "foo")
            if (System.getProperty("native") != "it") { // exclude IT tests if command is not: gradle -Dnative=it
                exclude(
                        "**/*IT.class"
                )
            }
        }
    }
}

tasks {
    withType<Wrapper> {
        gradleVersion = "5.6.2"
        distributionType = Wrapper.DistributionType.BIN
    }
}

defaultTasks("build")
