plugins {
    kotlin("js") version "1.9.22"
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
