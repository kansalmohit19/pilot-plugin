package com.mohitkansal

import org.gradle.api.Plugin
import org.gradle.api.Project

class KTextPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        val ext = project.extensions.create("ktext", KTextExtension::class.java).apply {
            enableValidation.convention(true)
            generateTranslations.convention(true)
        }

        project.afterEvaluate {
            if (ext.enableValidation.get()) {
                project.tasks.register(
                    "validateTranslations", ValidateTranslationsTask::class.java
                ) {
                    sourceFile.setFrom(ext.sourceFile)
                    targetFiles.setFrom(ext.targetFiles)
                }
            }

            if (ext.generateTranslations.get()) {
                project.tasks.register(
                    "generateTranslations", GenerateTranslationsTask::class.java
                ) {
                    sourceFile.setFrom(ext.sourceFile)
                    targetFiles.setFrom(ext.targetFiles)
                }
            }
        }
    }
}