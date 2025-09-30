package com.mohitkansal

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.gradle.api.DefaultTask
import org.gradle.api.file.ConfigurableFileCollection
import org.gradle.api.tasks.InputFiles
import org.gradle.api.tasks.TaskAction
import java.io.File

abstract class GenerateTranslationsTask : DefaultTask() {

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
            logger.lifecycle("${targetFile.name}: Generating Translations")

            val targetFileMap: MutableMap<String, String> = mapper.readValue(targetFile)

            val missingKeys = sourceFileMap.keys - targetFileMap.keys
            val keysOfEmptyValues = targetFileMap.filter { (k, v) ->
                v.isBlank() && sourceFileMap.containsKey(k)
            }.keys

            if (missingKeys.isNotEmpty() || keysOfEmptyValues.isNotEmpty()) {
                if (missingKeys.isNotEmpty()) {
                    for (key in missingKeys) {
                        targetFileMap[key] = sourceFileMap[key] ?: ""
                    }
                    logger.lifecycle("${targetFile.name}: Missing keys added")
                }
                if (keysOfEmptyValues.isNotEmpty()) {
                    for (key in keysOfEmptyValues) {
                        targetFileMap[key] = sourceFileMap[key] ?: ""
                    }
                    logger.lifecycle("${targetFile.name}: Empty values replaced")
                }
                writeToFile(sourceFileMap, targetFileMap, targetFile)
            }
            logger.lifecycle("${targetFile.name}: Translations Generated\n")
        }

        logger.lifecycle("All translations are valid")
    }

    private fun writeToFile(
        sourceFileMap: Map<String, String>, targetMap: Map<String, String>, file: File
    ) {
        val orderedMap = LinkedHashMap<String, String>()

        for ((key, value) in sourceFileMap) {
            orderedMap[key] = targetMap[key].toString()
        }

        val objectMapper = jacksonObjectMapper().writerWithDefaultPrettyPrinter()
        val jsonContent = objectMapper.writeValueAsString(orderedMap)

        file.writeText(jsonContent)
    }
}