# Quarkus qute template engine
This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/

## Maven

<!--

### Getting Started

```bash
mvn io.quarkus:quarkus-maven-plugin:1.3.2.Final:create \
    -DprojectGroupId=com.github.daggerok \
    -DprojectArtifactId=reactive-qute \
    -DprojectVersion=1.0.0-SNAPSHOT \
    -DclassName="com.github.daggerok.qute.IndexPage" \
    -Dextensions="resteasy-qute" \
    -DbuildTool=maven

cd reactive-qute && ./mvnw quarkus:dev
```

-->

### JVM

```bash
./mvnw clean ; ./mvnw ; ./mvnw docker:build docker:start
http :8080
./mvnw docker:stop docker:remove
```

### Native

```bash
./mvnw clean ; ./mvnw -Pnative -Dquarkus.native.container-build=true 
```

## Gradle

<!--

### Getting Started

```bash
mvn io.quarkus:quarkus-maven-plugin:1.3.2.Final:create \
    -DprojectGroupId=com.github.daggerok \
    -DprojectArtifactId=reactive-qute \
    -DprojectVersion=1.0.0-SNAPSHOT \
    -DclassName="com.github.daggerok.qute.IndexPage" \
    -Dextensions="resteasy-qute" \
    -DbuildTool=gradle

cd reactive-qute && ./mvnw quarkusDev
```

-->

### JVM

```bash
./gradlew clean quarkusBuild ; ./gradlew dockerBuild dockerRun
./gradlew dockerLogs
http :8080
./gradlew dockerStop
```

### Native

```bash
./gradlew clean quarkusBuild buildNative --docker-build=true ; QUARKUS_NATIVE=true ./gradlew dockerBuild dockerRun
```

## Resources

* https://quarkus.io/blog/qute/
* https://smallrye.io/smallrye-mutiny/
* https://quarkus.io/guides/vertx
* https://quarkus.io/guides/getting-started-reactive#mutiny
* https://quarkus.io/guides/context-propagation
* https://smallrye.io/smallrye-reactive-streams-operators/

<!--

# Gradle

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```
./gradlew quarkusDev
```

## Packaging and running the application

The application can be packaged using `./gradlew quarkusBuild`.
It produces the `reactive-qute-1.0.0-SNAPSHOT-runner.jar` file in the `build` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `build/lib` directory.

The application is now runnable using `java -jar build/reactive-qute-1.0.0-SNAPSHOT-runner.jar`.

If you want to build an _über-jar_, just add the `--uber-jar` option to the command line:
```
./gradlew quarkusBuild --uber-jar
```

## Creating a native executable

You can create a native executable using: `./gradlew buildNative`.

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: `./gradlew buildNative --docker-build=true`.

You can then execute your native executable with: `./build/reactive-qute-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/gradle-tooling#building-a-native-executable.

-->

<!--

# Maven

# reactive-qute project

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```
./mvnw quarkus:dev
```

## Packaging and running the application

The application can be packaged using `./mvnw package`.
It produces the `reactive-qute-1.0.0-SNAPSHOT-runner.jar` file in the `/target` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/lib` directory.

The application is now runnable using `java -jar target/reactive-qute-1.0.0-SNAPSHOT-runner.jar`.

## Creating a native executable

You can create a native executable using: `./mvnw package -Pnative`.

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: `./mvnw package -Pnative -Dquarkus.native.container-build=true`.

You can then execute your native executable with: `./target/reactive-qute-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/building-native-image.

-->
