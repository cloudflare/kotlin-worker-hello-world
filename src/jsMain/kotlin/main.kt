import org.w3c.dom.events.EventListener
import org.w3c.fetch.Request
import org.w3c.fetch.Response
import org.w3c.fetch.ResponseInit

fun main() {
    @Suppress("UNUSED_VARIABLE")
    val eventListener = EventListener {event ->
        event.asDynamic().respondWith(fetch(event.asDynamic().request as Request))
        Unit
    }
    js("addEventListener('fetch', eventListener)")
}

@OptIn(ExperimentalJsExport::class)
@JsExport
fun fetch(request: Request) : Response {
    val headers: dynamic = object {}
    headers["content-type"] = "text/plain"
    return Response(
        "Kotlin Worker hello world",
        ResponseInit(headers = headers)
    )
}
