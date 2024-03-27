# Kotlin hello world for Cloudflare Workers

Your Kotlin code in [main.kt](src/wasmJsMain/kotlin/main.kt), running on Cloudflare Workers

In addition to [Wrangler v2.x](https://github.com/cloudflare/wrangler2) you will need to install a JDK 8 or newer. [The easiest way to do this](https://www.jetbrains.com/guide/java/tips/download-jdk/) is using the free Community Edition of [IntelliJ IDEA](https://www.jetbrains.com/idea/download/).

## Wrangler

Configure the [wrangler.toml](wrangler.toml) by filling in the `account_id` from the Workers pages of your Cloudflare Dashboard.

Further documentation for Wrangler can be found [here](https://developers.cloudflare.com/workers/tooling/wrangler).

## Build & Deploy

After setting up your environment, run the following command:

```shell
./gradlew :compileProductionExecutableKotlinWasmJs
```

That will compile your code into a WebAssembly executable and JavaScript glue code, 
after which you can run `wrangler deploy` to push it to Cloudflare:

```shell
npx wrangler@latest deploy build/js/packages/kotlin-worker-hello-world-wasm-js/kotlin/kotlin-worker-hello-world-wasm-js.mjs
```

## Learn more

* [Kotlin/Wasm Overview](https://kotl.in/wasm/)
* [Kotlin/Wasm JavaScript interop](https://kotlinlang.org/docs/wasm-js-interop.html).
