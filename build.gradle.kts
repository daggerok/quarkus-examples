import com.avast.gradle.dockercompose.ComposeExtension
import com.avast.gradle.dockercompose.DockerComposePlugin
import io.quarkus.gradle.QuarkusPlugin
import org.gradle.api.tasks.testing.logging.TestLogEvent.*
import org.gradle.api.tasks.wrapper.Wrapper.DistributionType.BIN

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

    apply<QuarkusPlugin>()

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

    apply<DockerComposePlugin>()

    tasks["composeUp"].dependsOn("quarkusBuild")

    configure<ComposeExtension> {
        useComposeFiles = listOf("src/main/docker/docker-compose.yaml")
        removeImages = com.avast.gradle.dockercompose.RemoveImages.Local
        isIgnorePushFailure = true
        isRemoveContainers = true
        projectName = "quarkus"
        isRemoveOrphans = true
        isRemoveVolumes = true
        isForceRecreate = true
        isBuildBeforeUp = true
    }
}

tasks {
    withType<Wrapper> {
        gradleVersion = "5.6.2"
        distributionType = BIN
    }
}

defaultTasks("clean", "test", "build")
