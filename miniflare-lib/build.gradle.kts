plugins {
    alias(libs.plugins.kotlin.multiplatform)
}

kotlin {
    js(IR) {
        nodejs()
    }
    sourceSets {
        getByName("jsMain") {
            dependencies {
                implementation(npm("miniflare", libs.versions.miniflare.get()))
            }
        }
        getByName("jsTest") {
            dependencies {
                implementation(kotlin("test"))
                implementation(libs.kotlinx.coroutines.core)
                implementation(libs.kotlinx.coroutines.test)
            }
        }
    }
}
