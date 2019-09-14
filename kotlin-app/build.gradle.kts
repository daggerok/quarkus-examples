import com.avast.gradle.dockercompose.RemoveImages
import org.gradle.kotlin.dsl.provider.KotlinScriptPlugin

plugins {
    kotlin("jvm")
    id("com.github.ben-manes.versions")
    id("com.avast.gradle.docker-compose")
}

apply<io.quarkus.gradle.QuarkusPlugin>()
apply<org.jetbrains.kotlin.gradle.plugin.KotlinPlatformJvmPlugin>()

dependencies {
    implementation("io.vavr:vavr")
    implementation("io.quarkus:quarkus-jsonp")
    implementation("io.quarkus:quarkus-jsonb")
    implementation("io.quarkus:quarkus-resteasy")
    implementation("io.quarkus:quarkus-smallrye-health")
    implementation("io.quarkus:quarkus-smallrye-metrics")
    implementation("io.quarkus:quarkus-smallrye-openapi")
    implementation("io.quarkus:quarkus-kotlin")
    implementation(kotlin("stdlib-jdk8"))
    testImplementation("io.rest-assured:rest-assured")
    testImplementation("io.quarkus:quarkus-junit5")
}

tasks["composeUp"].dependsOn("quarkusBuild")

dockerCompose {
    useComposeFiles = listOf("src/main/docker/docker-compose.yaml")
    projectName = "quarkus"
    removeImages = RemoveImages.Local
    isRemoveContainers = true
    isRemoveOrphans = true
    isRemoveVolumes = true
    isForceRecreate = true
    isBuildBeforeUp = true
    isIgnorePushFailure = true
}
