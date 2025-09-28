# Git Version Plugin

[![Gradle](https://img.shields.io/badge/Gradle-7.0%2B-green?logo=gradle)](https://gradle.org/) 
[![Kotlin](https://img.shields.io/badge/Kotlin-DSL-blue?logo=kotlin)](https://kotlinlang.org/) 
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](../LICENSE.md)  

A lightweight Gradle plugin that automatically generates **code** and **name** directly from your Git history.  
Perfect for Android projects and any build setup where you want Git-driven versioning.

---

## Features

- **code** → Derived from the total number of Git commits (`git rev-list --count HEAD`).
- **name** → Dynamically generated (based on tags or commit count).
- **Zero config** → Just apply the plugin and use the values.
- **Safe defaults** → Falls back gracefully if Git is not available.

---

## If you like this plugin, don’t forget to star the repo!
[![GitHub](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)](https://github.com/kansalmohit19/pilot-plugin/tree/master/git-version)
