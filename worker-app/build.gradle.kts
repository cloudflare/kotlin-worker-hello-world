plugins {
    alias(libs.plugins.kotlin.multiplatform)
}

val kotlinJsOutputFilename = "worker-app.js"

kotlin {
    js(IR) {
        browser {
            webpackTask {
                mainOutputFileName = kotlinJsOutputFilename
            }
        }
        binaries.executable()
    }

    sourceSets {
        getByName("jsMain") {
            dependencies {
            }
        }
        getByName("jsTest") {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}

tasks {
    named("jsBrowserProductionWebpack") {
        val entrypointFile = "${layout.buildDirectory.asFile.get()}/dist/js/productionExecutable/entrypoint.js"
        outputs.file(entrypointFile)

        val jsEntrypoint = """
            // Import the compiled Kotlin/JS module
            import app from './$kotlinJsOutputFilename';
        
            // The entrypoint expected by Cloudflare
            export default {
              async fetch(request, env, ctx) {
                return app.main(request, env, ctx);
              },
            };
        """.trimIndent()

        doLast {
            File(entrypointFile).writeText(jsEntrypoint)
        }
    }
}

// This provides a way for worker-integration-test to wait for worker-app to be built
configurations.register("workerAppProduction") {
    isCanBeConsumed = true
    isCanBeResolved = false
}.also { configuration ->
    artifacts {
        val webpackTask = tasks.getByName("jsBrowserProductionWebpack")
        webpackTask.outputs.files.forEach { file ->
            add(configuration.name, file) {
                builtBy(webpackTask)
            }
        }
    }
}
