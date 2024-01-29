@file:JsModule("miniflare")

package com.cloudflare.miniflare

import org.w3c.fetch.RequestInit
import org.w3c.fetch.Response
import kotlin.js.Promise

// https://github.com/cloudflare/miniflare/blob/v3.20231016.0/packages/miniflare/README.md#class-miniflare
external class Miniflare(
    @Suppress("UnusedPrivateProperty") miniflareOptions: dynamic
) {
    fun dispatchFetch(
        url: String,
        init: RequestInit?
    ): Promise<Response>

    fun dispose(): Promise<Unit>
}
