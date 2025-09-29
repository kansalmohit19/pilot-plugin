## Installation

Add the plugin to your `plugins` block in module build.gradle.kts or in the root build.gradle.kts:

```kotlin
plugins {
    id("io.github.kansalmohit19.ktext") version "X.Y.Z"
}
```

## Usage

### Android example

In your `build.gradle.kts`:

```kotlin
plugins {
    id("io.github.kansalmohit19.ktext") version "X.Y.Z"
}

ktext {
    baseFile.setFrom(project.rootProject.file("resources/translations.en.json"))
    translationFiles.setFrom(
        project.rootProject.file("resources/translations.hi.json"),
        project.rootProject.file("resources/translations.es.json")
    )
}
```

## Example Output

```kotlin
./gradlew validateTranslations

Validating file: translations.hi.json
Validation done: translations.hi.json

Validating file: translations.es.json
Validation done: translations.es.json
```
