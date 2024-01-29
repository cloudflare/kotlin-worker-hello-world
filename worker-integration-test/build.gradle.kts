plugins {
    alias(libs.plugins.kotlin.multiplatform)
}

val generateBuildConfigTask = tasks.register("buildConfig") {
    // An improvement would be to get the file name from the task generating this file
    val inputFile = "worker-app/build/dist/js/productionExecutable/entrypoint.js"
    val generatedDir = "${layout.buildDirectory.asFile.get()}/generated"

    outputs.dir(File(generatedDir))

    doLast {
        val outputFile = File("$generatedDir/BuildConfig.kt")
        outputFile.parentFile.mkdirs()

        buildString {
            appendLine("// Generated file")
            appendLine()
            appendLine("object BuildConfig {")
            appendLine("    const val ENTRYPOINT_PATH: String = \"${inputFile}\"")
            appendLine("}")
        }.let { buildConfig ->
            outputFile.writeText(buildConfig)
        }
    }
}

kotlin {
    js(IR) {
        nodejs()
    }

    sourceSets {
        getByName("jsMain") {
            dependencies {
            }
        }
        getByName("jsTest") {
            kotlin.srcDir(generateBuildConfigTask)
            dependencies {
                implementation(kotlin("test"))
                implementation(libs.kotlinx.coroutines.core)
                implementation(libs.kotlinx.coroutines.test)
                implementation(projects.miniflareLib)
                implementation(
                    project(
                        mapOf(
                            "path" to ":worker-app",
                            "configuration" to "workerAppProduction"
                        )
                    )
                )
            }
        }
    }
}
