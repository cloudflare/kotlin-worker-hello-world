package com.cloudflare.miniflare

import org.w3c.fetch.RequestInit
import org.w3c.fetch.Response
import kotlin.js.Promise

// This is ignored internally by Miniflare, but we need some sort of URL to call dispatchFetch
val Miniflare.DEFAULT_URL
    get() = "http://localhost:8787"

fun Miniflare.dispatchFetchForPath(
    path: String,
    init: RequestInit? = null
): Promise<Response> {
    require(path.startsWith("/")) { "Path must start with a slash" }
    return dispatchFetch("$DEFAULT_URL$path", init)
}
