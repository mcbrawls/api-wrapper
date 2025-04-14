package net.mcbrawls.api.wrapper.server

import com.github.benmanes.caffeine.cache.Cache
import io.ktor.http.HttpHeaders
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respondText
import kotlinx.serialization.json.Json

suspend fun <K : Any, V : Any> Cache<K, V>.getSuspending(key: K, mapper: suspend (K) -> V): V {
  val presentEntry = getIfPresent(key)
  val value = presentEntry ?: mapper(key).also { value -> put(key, value) }

  return value
}

suspend inline fun <reified T : Any> ApplicationCall.respondJson(value: T) {
  response.headers.append(HttpHeaders.ContentType, "application/json")
  respondText(Json.encodeToString(value))
}
