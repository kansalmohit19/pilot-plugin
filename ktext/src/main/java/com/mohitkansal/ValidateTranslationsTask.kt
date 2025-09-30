package com.mohitkansal

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.gradle.api.DefaultTask
import org.gradle.api.file.ConfigurableFileCollection
import org.gradle.api.tasks.InputFiles
import org.gradle.api.tasks.TaskAction

abstract class ValidateTranslationsTask : DefaultTask() {

    @get:InputFiles
    abstract val sourceFile: ConfigurableFileCollection

    @get:InputFiles
    abstract val targetFiles: ConfigurableFileCollection

    @TaskAction
    fun validate() {
        val sourceFile = sourceFile.singleFile
        val targetFiles = targetFiles.files

        if (!sourceFile.exists()) {
            throw IllegalArgumentException("Source file not found!")
            return
        }

        if (targetFiles.isEmpty()) {
            throw IllegalArgumentException("No translation files provided!")
            return
        }


        val mapper = jacksonObjectMapper()
        val sourceFileMap: Map<String, String> = mapper.readValue(sourceFile)

        targetFiles.forEach { targetFile ->
            logger.lifecycle("${targetFile.name}: Validating file")

            val targetFileMap: Map<String, String> = mapper.readValue(targetFile)

            val missingKeys = sourceFileMap.keys - targetFileMap.keys
            val emptyValues = targetFileMap.filter { (k, v) ->
                v.isBlank() && sourceFileMap.containsKey(k)
            }.keys

            if (missingKeys.isNotEmpty() || emptyValues.isNotEmpty()) {
                logger.lifecycle("${targetFile.name}: Missing keys $missingKeys")
                logger.lifecycle("${targetFile.name}: Empty translations $emptyValues")
                throw RuntimeException("Translation validation failed!")
            }
            logger.lifecycle("${targetFile.name}: Validation done\n")
        }

        logger.lifecycle("All translations are valid")
    }
}