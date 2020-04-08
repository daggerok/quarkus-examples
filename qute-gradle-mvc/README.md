# qute-gradle-mvc project
This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/

## Quarkus Gradle Getting Started

```bash
mvn io.quarkus:quarkus-maven-plugin:1.3.2.Final:create \
    -DprojectGroupId=com.github.daggerok \
    -DprojectArtifactId=qute-gradle-mvc \
    -DprojectVersion=1.0.0-SNAPSHOT \
    -DclassName="com.github.daggerok.qute.IndexPage" \
    -Dextensions="resteasy-qute" \
    -DbuildTool=gradle
```

## jvm

```bash
./gradlew clean quarkusBuild ; ./gradlew dockerBuild dockerRun
```

## native

```bash
./gradlew clean quarkusBuild buildNative --docker-build=true ; QUARKUS_NATIVE=true ./gradlew dockerBuild dockerRun
```

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```
./gradlew quarkusDev
```

## Packaging and running the application

The application can be packaged using `./gradlew quarkusBuild`.
It produces the `qute-gradle-mvc-1.0.0-SNAPSHOT-runner.jar` file in the `build` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `build/lib` directory.

The application is now runnable using `java -jar build/qute-gradle-mvc-1.0.0-SNAPSHOT-runner.jar`.

If you want to build an _über-jar_, just add the `--uber-jar` option to the command line:
```
./gradlew quarkusBuild --uber-jar
```

## Creating a native executable

You can create a native executable using: `./gradlew buildNative`.

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: `./gradlew buildNative --docker-build=true`.

You can then execute your native executable with: `./build/qute-gradle-mvc-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/gradle-tooling#building-a-native-executable.
