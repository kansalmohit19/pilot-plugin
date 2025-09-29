package com.mohitkansal

import org.gradle.api.Plugin
import org.gradle.api.Project

class GitVersionPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        val ext = project.extensions.create(
            "gitVersion", GitVersionExtension::class.java, project, project.providers
        )

        // Log lazily using a task to avoid configuration cache problems
        project.tasks.register("generateGitVersion") {
            group = "versioning"
            description = "Generates the version info from Git"

            doLast {
                val code = ext.code.get()
                val name = ext.name.get()
                project.logger.lifecycle("GitVersion: code=$code, name=$name")
            }
        }
    }
}