## Installation

Add the plugin to your `plugins` block in your module `build.gradle.kts` (or root build.gradle.kts):

```kotlin
plugins {
    id("io.github.kansalmohit19.git-version") version "X.Y.Z"
}
```

## Plugin Configuration

### Inside your Gradle module:

```kotlin
// Optional
gitVersion {
    // Optional: multiply git-based versionCode
    multiplier.set(1000)   // default = 1000

    // Optional: manually override versionName
    versionName.set("2.1.0")  
}

```

### Usage in Android

```kotlin
android {
    defaultConfig {
        versionCode = gitVersion.code.get()
        versionName = gitVersion.name.get()
    }
}
```

## Built-in Task
### The plugin automatically provides a task:

```kotlin
./gradlew generateGitVersion
```

```kotlin
========== VERSION INFO ==========
Version Code: 65000
Version Name: 2.0.0
==================================
```

### Creating Your Own Custom Task
You can register your own task and use gitVersion values:
```kotlin
tasks.register("printVersionInfo") {
    doLast {
        println("========== VERSION INFO ==========")
        println("Version Code: ${gitVersion.code.get()}")
        println("Version Name: ${gitVersion.name.get()}")
        println("==================================")
    }
}

```