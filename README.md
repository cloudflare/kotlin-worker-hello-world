# Kotlin hello world for Cloudflare Workers

Your Kotlin code in [main.kt](https://github.com/cloudflare/kotlin-worker-hello-world/blob/master/src/main/kotlin/main.kt), running on Cloudflare Workers

In addition to [Wrangler](https://github.com/cloudflare/wrangler) you will need to install Kotlin, including a JDK and support for Gradle projects. The easiest way to do this is using the free Community Edition of [IntelliJ IDEA](https://kotlinlang.org/docs/tutorials/jvm-get-started.html).

#### Wrangler

To generate using [wrangler](https://github.com/cloudflare/wrangler)

```
wrangler generate projectname https://github.com/cloudflare/kotlin-worker-hello-world
```

Further documentation for Wrangler can be found [here](https://developers.cloudflare.com/workers/tooling/wrangler).

#### Gradle

After setting up Kotlin per the linked instructions above,

```
cd projectname
./gradlew buildWorker
```

That will compile your code and package it into index.js, after which you can run `wrangler publish` to push it to Cloudflare.

For more information on interop between Kotlin and Javascript, see the [Kotlin docs](https://kotlinlang.org/docs/reference/js-interop.html).  Regarding coroutines, see [this issue with workaround](https://github.com/cloudflare/kotlin-worker-hello-world/issues/2)
