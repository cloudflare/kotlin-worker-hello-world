import org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootExtension
import org.jetbrains.kotlin.gradle.targets.js.yarn.YarnRootExtension

plugins {
    alias(libs.plugins.kotlin.multiplatform) apply false
}

rootProject.plugins.withType(org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootPlugin::class.java) {
    rootProject.the<NodeJsRootExtension>().versions.webpackCli.version = libs.versions.webpack.cli.get()
}

rootProject.plugins.withType<org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootPlugin> {
    rootProject.the<NodeJsRootExtension>().apply {
        download = project.property("CLOUDFLARE_IS_INSTALL_NODE").toString().toBoolean()
        nodeVersion = libs.versions.node.get()
    }
}

rootProject.plugins.withType<org.jetbrains.kotlin.gradle.targets.js.yarn.YarnPlugin> {
    rootProject.the<YarnRootExtension>().apply {
        download = project.property("CLOUDFLARE_IS_INSTALL_YARN").toString().toBoolean()
    }
}
