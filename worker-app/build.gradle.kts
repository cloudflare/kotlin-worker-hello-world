plugins {
    alias(libs.plugins.kotlin.multiplatform)
}

group = "org.example"
version = "1.0-SNAPSHOT"

kotlin {
    js(IR) {
        nodejs {
        }
        binaries.executable()
    }
}
