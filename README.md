# Kotlin hello world for Cloudflare Workers

Your Kotlin code in [Application.kt](worker-app/src/jsMain/kotlin/Application.kt), running on Cloudflare Workers.

You will need to install Kotlin, including a JDK and support for Gradle projects. The easiest way to do this is using the free Community Edition of [IntelliJ IDEA](https://kotlinlang.org/docs/tutorials/jvm-get-started.html).

## Wrangler
You will need to install wrangler, e.g. `npm install wrangler`

Further documentation for Wrangler can be found [here](https://developers.cloudflare.com/workers/tooling/wrangler).

## Gradle
After setting up Kotlin per the linked instructions above,

```
./gradlew assemble
```

That will compile your code and package it into a JavaScript executable, after which you can run `wrangler dev` to start a localhost server for testing.

To publish to Cloudflare, run `wrangler publish`

For more information on interop between Kotlin and Javascript, see the [Kotlin docs](https://kotlinlang.org/docs/reference/js-interop.html).  Regarding coroutines, see [this issue and workaround](https://github.com/cloudflare/kotlin-worker-hello-world/issues/2)
