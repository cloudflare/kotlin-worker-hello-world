import org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootExtension
import org.jetbrains.kotlin.gradle.targets.js.npm.tasks.KotlinNpmInstallTask
import org.jetbrains.kotlin.gradle.targets.js.ir.*

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

        // Comment the next line to turn off optimization by Binaryen
        applyBinaryen()

        // Workaround for https://youtrack.jetbrains.com/issue/KT-66972
        compilations
            .configureEach {
                binaries.withType<Executable>().configureEach {
                        linkTask.configure {
                            val moduleName = linkTask.flatMap {
                                it.compilerOptions.moduleName
                            }
                            val fileName = moduleName.map { module ->
                                "$module.uninstantiated.mjs"
                            }

                            val mjsFile: Provider<RegularFile> = linkTask.flatMap {
                                it.destinationDirectory.file(fileName.get())
                            }
                            
                            doLast {
                                val module = moduleName.get()
                                val file = mjsFile.get().asFile
                                val text = file.readText()
                                val newText = text
                                    .replace("if \\(!isNodeJs && !isStandaloneJsVM && !isBrowser\\) \\{[^\\}]*\\}".toRegex(), "")
                                    .replace(
                                        "(if \\(isBrowser\\) \\{\\s*wasmInstance[^\\}]*\\})".toRegex(),
                                        """
                                        $1
                                        
                                              const isWorker = navigator.userAgent.includes("Workers")
                                              if (isWorker) {
                                                  const { default: wasmModule } = await import('./$module.wasm');
                                                  wasmInstance = (await WebAssembly.instantiate(wasmModule, importObject));
                                              }
                                        """.trimIndent()
                                    )

                                file.writeText(newText)
                            }
                        }
                    }
            }
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
