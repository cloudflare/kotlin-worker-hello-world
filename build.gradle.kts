plugins {
    id("org.jetbrains.kotlin.js") version "1.3.72"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-js"))
    testImplementation(kotlin("test-js"))
}

kotlin.target.browser {
}


tasks.register("buildWorker") {
    dependsOn("browserProductionWebpack")
    doLast {
        /* Kotlin js output assumes window exists, which it won't on Workers. Hack around it */
        file("$projectDir/index.js").writeText("const window=this; " + file("$projectDir/build/distributions/${rootProject.name}.js").readText())
    }
}
