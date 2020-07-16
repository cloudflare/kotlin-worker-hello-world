import org.w3c.fetch.Response
import org.w3c.fetch.ResponseInit
import org.w3c.workers.FetchEvent
import kotlin.js.Promise

external fun addEventListener(type: String, f: (FetchEvent) -> Unit)

fun main() {
    addEventListener("fetch") { event: FetchEvent ->
        val headers: dynamic = object {}
        headers["content-type"] = "text/plain"
        event.respondWith(
            Promise.resolve(
                Response(
                    "Kotlin Worker hello world",
                    ResponseInit(headers = headers)
                )
            )
        )
    }
}
