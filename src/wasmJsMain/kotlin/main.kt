import org.w3c.fetch.Headers
import org.w3c.fetch.Request
import org.w3c.fetch.Response
import org.w3c.fetch.ResponseInit

@OptIn(ExperimentalJsExport::class)
@JsExport
fun fetch(request: Request) : Response {
    val headers = Headers()
    headers.append("content-type", "text/plain")
    return Response(
        "Hello from Kotlin/Wasm Worker".toJsString(),
        ResponseInit(headers = headers)
    )
}
