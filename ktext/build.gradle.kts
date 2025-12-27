plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
    id("com.gradle.plugin-publish") version "1.2.1"
}

group = "io.github.kansalmohit19"
version = "1.0.3"

gradlePlugin {
    website.set("https://kansalmohit19.github.io/pilot-plugin/ktext")
    vcsUrl.set("https://github.com/kansalmohit19/pilot-plugin/tree/master/ktext")

    plugins {
        create("ktextPlugin") {
            id = "io.github.kansalmohit19.ktext"
            implementationClass = "com.mohitkansal.KTextPlugin"
            displayName = "KText Translation Plugin"
            description = "A lightweight Gradle plugin for validating and managing translations across multiple languages and modules in your project. Supports JSON and `strings.xml` files to ensure all keys are consistent between languages."
            tags.set(listOf("android", "kotlin", "kmp", "translations", "localization", "i18n", "multilingual", "json", "strings.xml"))
        }
    }
}

dependencies {
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.20.0")
}
