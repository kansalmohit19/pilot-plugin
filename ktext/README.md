# KText Plugin

[![Gradle](https://img.shields.io/badge/Gradle-7.0%2B-green?logo=gradle)](https://gradle.org/)
[![Kotlin](https://img.shields.io/badge/Kotlin-DSL-blue?logo=kotlin)](https://kotlinlang.org/)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](../LICENSE.md)

A lightweight Gradle plugin for **validating and managing translations** across multiple languages and modules in your project.  
Supports JSON and `strings.xml` files to ensure all keys are consistent between languages.

---

## Features

- **Cross-language validation** → Detect missing keys/values between source and target translation files (e.g., `en.json` vs `hi.json`).
- **Multi-format support** → Works with JSON and Android `strings.xml`.
- **Multi-module friendly** → Aggregate translation validation across modules.
- **Configurable** → Easily point to your translation files in the Gradle build.
- **Safe defaults** → Skips validation gracefully if files are missing.

---

## If you like this plugin, don’t forget to star the repo!
[![GitHub](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)](https://github.com/kansalmohit19/pilot-plugin/tree/master/ktext)
