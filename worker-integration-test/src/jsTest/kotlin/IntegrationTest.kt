import kotlinx.coroutines.await
import kotlinx.coroutines.test.runTest
import com.cloudflare.miniflare.Script
import com.cloudflare.miniflare.Miniflare
import com.cloudflare.miniflare.MiniflareOptionsFactory
import com.cloudflare.miniflare.dispatchFetchForPath
import BuildConfig
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class IntegrationTest {
    @BeforeTest
    fun fixEnv() {
        // There's an inconsistency in the working directory; this ensures it is the root Gradle project
        js("process.chdir(process.env[\"PWD\"])")
    }

    @Test
    fun hello_world() =
        runTest {
            val miniflare =
                Miniflare(
                    MiniflareOptionsFactory.new(
                        Script.File(BuildConfig.ENTRYPOINT_PATH),
                        modules = true
                    )
                )
            try {
                val response = miniflare.dispatchFetchForPath("/").await()
                assertEquals(200, response.status)
                assertEquals("Hello world", response.text().await())
            } finally {
                miniflare.dispose().await()
            }
        }
}
