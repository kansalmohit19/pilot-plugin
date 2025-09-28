## Installation

Add the plugin to your `plugins` block in module build.gradle.kts or in the root build.gradle.kts:

```kotlin
plugins {
    id("io.github.kansalmohit19.git-version") version "X.Y.Z"
}
```

## Usage

### 1. Android example

```kotlin
android {
    defaultConfig {
        versionCode = gitVersion.code.get()
        versionName = gitVersion.name.get()
    }
}
```

### 2. Custom task example

```kotlin
tasks.register("printGitVersion") {
    doLast {
        println("App Version → Code=${gitVersion.code.get()}, Name=${gitVersion.name.get()}")
    }
}
```

## Example Output

```kotlin
./gradlew printGitVersion

GitVersion: code=38, name=3.8
```
