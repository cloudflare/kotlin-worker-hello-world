@file:OptIn(ExperimentalJsStatic::class, ExperimentalJsExport::class)

import org.w3c.fetch.Request
import org.w3c.fetch.Response
import org.w3c.fetch.ResponseInit

@JsExport
class HelloWorker {
    companion object {
        @JsStatic
        fun fetch(request: Request, env: dynamic, ctx: dynamic): Response {
            val headers: dynamic = object {}
            headers["content-type"] = "text/plain"
            return Response(
                "Kotlin Worker hello world",
                ResponseInit(headers = headers)
            )
        }
    }
}
