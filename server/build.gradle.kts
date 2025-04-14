plugins {
  kotlin("jvm")
  alias(libs.plugins.kotlinx.serialization)
  alias(libs.plugins.ktor)
}

dependencies {
  implementation(libs.bundles.ktor.server)
  implementation(libs.bundles.ktor.client)
  implementation(libs.ktor.client.auth)
  implementation(libs.ktor.client.logging)
  implementation(libs.logback)
  implementation(libs.caffeine)
  implementation(libs.bundles.kotlinx)
  implementation(projects.common)
}

application {
  mainClass = "net.mcbrawls.api.wrapper.server.EntrypointKt"
}
