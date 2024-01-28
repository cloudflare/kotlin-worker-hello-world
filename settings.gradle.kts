@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    @Suppress("UnstableApiUsage")
    repositories {
        mavenCentral()
    }
}

rootProject.name = "Cloudflare"

include("worker-app")
