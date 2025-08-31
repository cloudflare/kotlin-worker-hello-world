plugins {
    kotlin("multiplatform") version "2.2.10"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

kotlin {
    js(IR) {
        nodejs {
        }
        binaries.executable()
    }
}
