package com.mohitkansal

import org.gradle.api.Project
import org.gradle.api.provider.Property
import org.gradle.api.provider.Provider
import org.gradle.api.provider.ProviderFactory
import javax.inject.Inject

open class GitVersionExtension @Inject constructor(
    private val project: Project, private val providers: ProviderFactory
) {

    val versionName: Property<String> = project.objects.property(String::class.java).convention("")
    val multiplier: Property<Int> = project.objects.property(Int::class.java).convention(1000)

    // Version code calculated from git
    val code: Provider<Int> = providers.provider {
        val gitCount = runGit("rev-list --count HEAD").toIntOrNull() ?: 1
        gitCount * multiplier.get()
    }

    // Auto-generated versionName (fallback)
    private val autoName: Provider<String> = code.map { versionCode ->
        versionCode.toString()
            .map { it.toString() }
            .joinToString(".")
    }

    // Final version name → userProvided or auto-generated
    val name: Provider<String> = versionName.flatMap { custom ->
        if (custom.isBlank()) autoName else providers.provider { custom }
    }

    private fun runGit(command: String, ignoreError: Boolean = false): String {
        return try {
            val process =
                ProcessBuilder("git", *command.split(" ").toTypedArray()).directory(project.rootDir)
                    .redirectErrorStream(true).start()
            val output = process.inputStream.bufferedReader().readText().trim()
            process.waitFor()
            if (process.exitValue() != 0 && !ignoreError) "" else output
        } catch (e: Exception) {
            ""
        }
    }
}
