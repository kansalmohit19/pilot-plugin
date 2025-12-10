plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
    id("com.gradle.plugin-publish") version "1.2.1"
}

group = "io.github.kansalmohit19"
version = "0.0.4"

gradlePlugin {
    website.set("https://kansalmohit19.github.io/pilot-plugin/git-version")
    vcsUrl.set("https://github.com/kansalmohit19/pilot-plugin/tree/master/git-version")

    plugins {
        create("gitVersionPlugin") {
            id = "io.github.kansalmohit19.git-version"
            implementationClass = "com.mohitkansal.GitVersionPlugin"
            displayName = "Git Version Plugin"
            description = "A Gradle plugin that automatically generates code and name directly from your Git history."
            tags.set(listOf("android", "versioning", "git"))
        }
    }
}
