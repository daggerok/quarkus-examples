import com.avast.gradle.dockercompose.RemoveImages
import org.gradle.api.tasks.testing.logging.TestLogEvent.*

object Versions {
    const val vavr = "0.10.2"
    const val gradle = "5.6.2"
    const val quarkus = "0.21.2"
    const val versionsPlugin = "0.25.0"
    const val dockerComposePlugin = "0.9.4"
}

buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("io.quarkus:quarkus-gradle-plugin:${Versions.quarkus}")
    }
}

plugins {
    idea
    java
    id("com.avast.gradle.docker-compose") version Versions.dockerComposePlugin apply false
    id("com.github.ben-manes.versions") version Versions.versionsPlugin apply false
    // // for some reasons, test wont work with new style quarkus plugin declaration:
    // https://github.com/quarkusio/quarkus/issues/3552#issuecomment-524225607
    // id("io.quarkus") version Versions.quarkus apply false
}

allprojects {
    version = "0.0.2-SNAPSHOT"
    group = "com.github.daggerok.quarkus"
}

subprojects {
    apply<JavaPlugin>()

    java {
        sourceCompatibility = JavaVersion.VERSION_1_8
        // targetCompatibility = JavaVersion.VERSION_1_8
    }

    repositories {
        mavenCentral()
        //// not needed, all dependencies should be in central
        //maven(url = uri("https://repository.jboss.org/nexus/content/groups/public"))
    }

    apply<io.quarkus.gradle.QuarkusPlugin>()

    dependencies {
        implementation(enforcedPlatform("io.quarkus:quarkus-bom:${Versions.quarkus}"))
        implementation(enforcedPlatform("io.vavr:vavr:${Versions.vavr}"))
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
        gradleVersion = Versions.gradle
        distributionType = Wrapper.DistributionType.BIN
    }
}

defaultTasks("build")
