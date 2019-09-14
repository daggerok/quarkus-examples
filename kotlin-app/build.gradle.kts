plugins {
    kotlin("jvm")
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
