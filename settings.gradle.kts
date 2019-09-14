rootProject.name = "quarkus-examples"
include(
        ":app-1",
        ":app-2"
)
// required by quarkus gradle plugin:
pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
    resolutionStrategy {
        eachPlugin {
            if (requested.id.id == "io.quarkus") {
                useModule("io.quarkus:quarkus-gradle-plugin:${requested.version}")
            }
        }
    }
}
