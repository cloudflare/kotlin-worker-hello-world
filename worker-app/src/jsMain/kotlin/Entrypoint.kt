import org.w3c.fetch.Request
import org.w3c.fetch.Response
import kotlin.js.Promise

// This is the Kotlin entrypoint, which the Javascript entrypoint (see build.gradle.kts) will call.
// The unused parameters are Cloudflare-specific and can be used to access additional Cloudflare features
@OptIn(ExperimentalJsExport::class)
@JsExport
fun main(
    request: Request,
    @Suppress("UNUSED_PARAMETER")
    cloudflareEnv: dynamic,
    @Suppress("UNUSED_PARAMETER")
    cloudflareCtx: dynamic
): Promise<Response> = application(request)
