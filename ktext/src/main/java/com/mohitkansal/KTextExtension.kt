package com.mohitkansal

import org.gradle.api.file.ConfigurableFileCollection

abstract class KTextExtension {
    abstract val baseFile: ConfigurableFileCollection

    abstract val translationFiles: ConfigurableFileCollection
}