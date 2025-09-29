# KText Plugin Publishing

This guide explains how to publish the **KText Plugin** either to **Maven Local** (for testing) or to the **Gradle Plugin Portal** (for distribution).

---

## Setup Gradle Plugin Portal Credentials

You need to provide your **Gradle Plugin Portal API keys**.  
Add the following inside your `~/.gradle/gradle.properties` file (machine-wide, not in the project repo):

```properties
gradle.publish.key=YOUR_KEY
gradle.publish.secret=YOUR_SECRET
```
Replace YOUR_KEY and YOUR_SECRET with the values you can get these keys from your [Gradle Plugin Portal account](https://plugins.gradle.org/me).


## Publishing Tasks
**1. Publish to Maven Local (for testing)**

```kotlin
./gradlew publishToMavenLocal
```

This will install the plugin into your local Maven repository (usually ~/.m2/repository).
You can then use it in a sample project with:

```kotlin
plugins {
    id("io.github.kansalmohit19.ktext") version "X.Y.Z"
}
```

and add:

```kotlin
pluginManagement {
    repositories {
        mavenLocal()
        gradlePluginPortal()
    }
}
```

**2. Publish to Gradle Plugin Portal**

```kotlin   
./gradlew publishPlugins
```

This uploads the plugin to the official Gradle Plugin Portal. Once published, others can use it with:

```kotlin
plugins {
    id("io.github.kansalmohit19.ktext") version "X.Y.Z"
}
```
