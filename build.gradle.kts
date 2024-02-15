import org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootExtension
import org.jetbrains.kotlin.gradle.targets.js.npm.tasks.KotlinNpmInstallTask

plugins {
    kotlin("multiplatform") version "1.9.22"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

kotlin {
    wasmJs {
        nodejs {
        }
        binaries.executable()

        // Uncomment the next line to apply Binaryen and get optimized wasm binaries
        // applyBinaryen()
    }
}

// To run in Node.js
rootProject.the<NodeJsRootExtension>().apply {
    nodeVersion = "22.0.0-nightly2024021536dcd399c0"
    nodeDownloadBaseUrl = "https://nodejs.org/download/nightly"
}
tasks.withType<KotlinNpmInstallTask>().configureEach {
    args.add("--ignore-engines")
}
