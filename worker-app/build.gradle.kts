plugins {
    kotlin("js") version "1.9.22"
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
