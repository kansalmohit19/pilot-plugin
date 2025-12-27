# Pilot Plugins

This repository contains multiple **Pilot plugins** maintained in a single codebase (monorepo). Each plugin is independently versioned and released.

The goal of this repository is to demonstrate how reusable libraries and plugins can be **structured, documented, versioned, and extended** over time.

See the [LICENSE.md](LICENSE.md) file for licensing details.

## Plugins

### git-version

A lightweight Gradle plugin that automatically generates **code** and **name** directly from your Git history. Perfect for Android projects and any build setup where you want Git-driven versioning.

For more details, refer to [`git-version/README.md`](git-version/README.md).

### ktext

A lightweight Gradle plugin for **validating and managing translations** across multiple languages and modules in your project. Supports JSON and `strings.xml` files to ensure all keys are consistent between languages.

For more details, refer to [`ktext/README.md`](ktext/README.md).