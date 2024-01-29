package com.cloudflare.miniflare

// This works better than trying to define external classes/interfaces because it avoids type checking issues
// when passing objects between Kotlin and JS with optional properties.
// https://github.com/cloudflare/miniflare/blob/v3.20231016.0/packages/miniflare/README.md#type-miniflareoptions
object MiniflareOptionsFactory {
    // https://developers.cloudflare.com/workers/platform/compatibility-dates/
    const val DEFAULT_COMPATIBILITY_DATE = "2023-12-01"

    fun new(
        script: Script,
        cf: Boolean = false,
        modules: Boolean? = true,
        compatibilityDate: String = DEFAULT_COMPATIBILITY_DATE,
        d1Databases: Array<String>? = null,
    ): dynamic {
        val options: dynamic = object {}

        when (script) {
            is Script.Inline -> {
                options.script = script.script
                script.scriptPath?.let { options.scriptPath = it }
            }
            is Script.File -> options.scriptPath = script.path
        }

        modules?.let { options.modules = it }

        options.cf = cf
        options.compatibilityDate = compatibilityDate
        d1Databases?.let { options.d1Databases = it }

        return options
    }
}

sealed class Script {
    data class Inline(
        val script: String,
        val scriptPath: String? = null
    ) : Script()

    data class File(val path: String) : Script()
}
