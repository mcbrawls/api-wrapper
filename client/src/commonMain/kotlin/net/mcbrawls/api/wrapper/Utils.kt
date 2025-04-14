package net.mcbrawls.api.wrapper

import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.appendPathSegments
import io.ktor.http.buildUrl
import io.ktor.http.takeFrom

/** Fetches the Brawls wrapper API at the given [path] and decodes the result as [T]. */
public suspend inline fun <reified T> BrawlsAPI.fetch(path: String): T {
  return httpClient.get(
    buildUrl {
      takeFrom(serverUrl)
      appendPathSegments(path.split("/"))
    },
  ).body()
}
