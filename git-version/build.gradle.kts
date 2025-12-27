plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
    id("com.gradle.plugin-publish") version "1.2.1"
}

group = "io.github.kansalmohit19"
version = "1.0.2"

gradlePlugin {
    website.set("https://kansalmohit19.github.io/pilot-plugin/git-version")
    vcsUrl.set("https://github.com/kansalmohit19/pilot-plugin/tree/master/git-version")

    plugins {
        create("gitVersionPlugin") {
            id = "io.github.kansalmohit19.git-version"
            implementationClass = "com.mohitkansal.GitVersionPlugin"
            displayName = "Git Version Plugin"
            description = "A lightweight Gradle plugin that automatically generates code and name directly from your Git history. Perfect for Android projects and any build setup where you want Git-driven versioning."
            tags.set(listOf("android", "versioning", "git"))
        }
    }
}
