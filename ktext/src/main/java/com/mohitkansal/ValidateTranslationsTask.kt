package com.mohitkansal

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.gradle.api.DefaultTask
import org.gradle.api.file.ConfigurableFileCollection
import org.gradle.api.tasks.InputFiles
import org.gradle.api.tasks.TaskAction

abstract class ValidateTranslationsTask : DefaultTask() {

    @get:InputFiles
    abstract val baseFile: ConfigurableFileCollection

    @get:InputFiles
    abstract val translationFiles: ConfigurableFileCollection

    @TaskAction
    fun validate() {
        val baseFile = baseFile.singleFile
        val translationFiles = translationFiles.files

        if (!baseFile.exists()) {
            throw IllegalArgumentException("Base file not found!")
        }

        if (translationFiles.isEmpty()) {
            throw IllegalArgumentException("No translation files provided!")
            return
        }

        /*files.forEach { file ->
            logger.lifecycle("Validating file: ${file.absolutePath}")
            // Add JSON comparison logic here
        }

        val enFile = project.rootProject.file("resources/translations.en.json")
        val hiFile = project.rootProject.file("resources/translations.hi.json")

        if (!enFile.exists() || !hiFile.exists()) {
            throw IllegalArgumentException("Translation files not found! ${enFile.exists()} ${hiFile.exists()}")
        }*/


        val mapper = jacksonObjectMapper()
        val baseLanguageFileMap: Map<String, String> = mapper.readValue(baseFile)

        translationFiles.forEach { file ->
            logger.lifecycle("Validating file: ${file.name}")

            val translationFileMap: Map<String, String> = mapper.readValue(file)

            val missingKeys = baseLanguageFileMap.keys - translationFileMap.keys
            val emptyValues = translationFileMap.filter { (k, v) ->
                v.isBlank() && baseLanguageFileMap.containsKey(k)
            }.keys

            if (missingKeys.isNotEmpty() || emptyValues.isNotEmpty()) {
                logger.lifecycle("Missing keys in ${file.name}: $missingKeys")
                logger.lifecycle("Empty translations in ${file.name}: $emptyValues")
                throw RuntimeException("Translation validation failed!")
            }
            logger.lifecycle("Validation done: ${file.name}\n")
        }

        logger.lifecycle("All translations are valid")
    }
}