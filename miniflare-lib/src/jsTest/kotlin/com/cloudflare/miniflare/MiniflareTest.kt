package com.cloudflare.miniflare

import kotlinx.coroutines.await
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

private const val SCRIPT = """
addEventListener("fetch", (event) => {
    event.respondWith(new Response("Hello Miniflare!"));
})
"""

class MiniflareTest {
    @Test
    fun make_request() =
        runTest {
            val miniflare = Miniflare(MiniflareOptionsFactory.new(Script.Inline(SCRIPT), modules = false))
            try {
                val response = miniflare.dispatchFetchForPath("/").await()
                assertEquals(200, response.status)
                assertEquals("Hello Miniflare!", response.text().await())
            } finally {
                miniflare.dispose().await()
            }
        }
}
