import org.jetbrains.kotlin.gradle.dsl.KotlinJsCompile

plugins {
    kotlin("multiplatform") version "2.2.10"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

kotlin {
    js {
        nodejs()
        binaries.executable()
    }
}

// https://kotlinlang.org/docs/js-project-setup.html#support-for-es2015-features
tasks.withType<KotlinJsCompile>().configureEach {
    compilerOptions {
        target.set("es2015")
    }
}

tasks.named("jsProductionExecutableCompileSync") {
    val entrypointFile = "${layout.buildDirectory.asFile.get()}/js/packages/kotlin-worker-hello-world/kotlin/kotlin-worker-hello-world.mjs"
    outputs.file(entrypointFile)

    val jsEntrypoint = """
            // The entrypoint expected by Cloudflare
            export default {
                async fetch(request, env, ctx) {
                    return HelloWorker.fetch(request, env, ctx);
                },
            };
        """.trimIndent()

    doLast {
        File(entrypointFile).appendText(jsEntrypoint)
    }
}
