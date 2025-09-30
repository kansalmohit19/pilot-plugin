package com.mohitkansal

import org.gradle.api.file.ConfigurableFileCollection
import org.gradle.api.provider.Property

abstract class KTextExtension {
    abstract val sourceFile: ConfigurableFileCollection

    abstract val targetFiles: ConfigurableFileCollection

    abstract val enableValidation: Property<Boolean>

    abstract val generateTranslations: Property<Boolean>
}