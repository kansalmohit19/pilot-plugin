package com.mohitkansal

import org.gradle.api.file.ConfigurableFileCollection

abstract class KTextExtension {
    abstract val sourceFile: ConfigurableFileCollection

    abstract val targetFiles: ConfigurableFileCollection
}