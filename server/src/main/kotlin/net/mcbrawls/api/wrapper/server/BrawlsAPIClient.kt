package net.mcbrawls.api.wrapper.server

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BasicAuthCredentials
import io.ktor.client.plugins.auth.providers.basic
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json

val client: HttpClient = HttpClient(CIO) {
  install(HttpTimeout) {
    requestTimeoutMillis = 100000
  }

  install(ContentNegotiation) {
    json()
  }

  install(Auth) {
    basic {
      credentials {
        BasicAuthCredentials(apiUser, apiToken)
      }

      realm = "Access to the '/' path"
    }
  }
}

const val BRAWLS_API_URL: String = "https://api.mcbrawls.net/v3"

val apiUser: String by lazy {
  System.getenv("BRAWLS_API_USER")
}

val apiToken: String by lazy {
  System.getenv("BRAWLS_API_TOKEN")
}

suspend inline fun <reified T> fetchBrawlsApi(route: String): T {
  return client.get("$BRAWLS_API_URL/$route").body<T>()
}
