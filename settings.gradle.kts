rootProject.name = "quarkus-examples"
include(
        ":kotlin-app",
        ":app-2",
        ":app-1"
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
