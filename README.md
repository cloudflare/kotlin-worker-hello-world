# Kotlin hello world for Cloudflare Workers

Your Kotlin code in [main.kt](https://github.com/cloudflare/kotlin-worker-hello-world/blob/master/src/main/kotlin/main.kt), running on Cloudflare Workers

In addition to [Wrangler v2.x](https://github.com/cloudflare/wrangler2) you will need to install Kotlin, including a JDK and support for Gradle projects. The easiest way to do this is using the free Community Edition of [IntelliJ IDEA](https://kotlinlang.org/docs/tutorials/jvm-get-started.html).

## Wrangler

Documentation for Wrangler can be found [here](https://developers.cloudflare.com/workers/tooling/wrangler).

To test the build locally, run it in dev.

```shell
wrangler dev
```

To deploy the example, use the following command.

```shell
wrangler deploy
```

For more information on interop between Kotlin and Javascript, see the [Kotlin docs](https://kotlinlang.org/docs/js-interop.html).  Regarding coroutines, see [this issue and workaround](https://github.com/cloudflare/kotlin-worker-hello-world/issues/2)
