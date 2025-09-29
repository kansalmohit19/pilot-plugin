package com.mohitkansal

import org.gradle.api.Project
import org.gradle.api.provider.Provider
import org.gradle.api.provider.ProviderFactory
import javax.inject.Inject

open class GitVersionExtension @Inject constructor(
    private val project: Project, private val providers: ProviderFactory
) {
    val code: Provider<Int> = providers.provider {
        runGit("rev-list --count HEAD").toIntOrNull() ?: 1
    }

    /*val name: Provider<String> = providers.provider {
        val tag = runGit("describe --tags --abbrev=0", ignoreError = true)
        val branch = runGit("rev-parse --abbrev-ref HEAD", ignoreError = true)

        when {
            tag.isNotEmpty() && branch != "HEAD" -> "$tag-SNAPSHOT"
            tag.isNotEmpty() -> tag
            else -> "0.1.0-SNAPSHOT"
        }
    }*/

    val name: Provider<String> = code.map { versionCode ->
        versionCode.toString()
            //.padStart(6, '0')
            .map { it.toString() }.joinToString(".")
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
