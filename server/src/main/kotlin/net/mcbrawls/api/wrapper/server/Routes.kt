package net.mcbrawls.api.wrapper.server

import com.github.benmanes.caffeine.cache.Cache
import com.github.benmanes.caffeine.cache.Caffeine
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.response.respond
import io.ktor.server.routing.Routing
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import net.mcbrawls.api.wrapper.ChatStatistics
import net.mcbrawls.api.wrapper.Leaderboard
import net.mcbrawls.api.wrapper.PlayerProfile
import java.time.Duration

val routeResponseCache: Cache<String, Any> = Caffeine
  .newBuilder()
  .expireAfterWrite(Duration.ofMinutes(5))
  .build()

private inline fun <reified T : Any> Routing.brawlsApi(route: String, targetRoute: String, cache: Cache<String, Any> = routeResponseCache) {
  get(route) {
    val result = cache.getSuspending(targetRoute) {
      fetchBrawlsApi<T>(targetRoute)
    } as T

    call.respondJson(result)
  }
}

private inline fun <reified T : Any> Routing.brawlsApi(route: String, targetRoute: String, vararg params: String) {
  get(route) {
    val parameterValues = params.associate { param ->
      val value = call.parameters[param.removeSuffix("?")]

      if (value == null && !param.endsWith("?")) {
        call.respond(HttpStatusCode.BadRequest, "missing parameter '$param'")
        return@get
      }

      param to value
    }

    var rewrittenTargetRoute = targetRoute
    parameterValues.forEach { (param, value) ->
      rewrittenTargetRoute = if (value == null) {
        rewrittenTargetRoute.replace("/{$param}", "")
      } else {
        rewrittenTargetRoute.replace("{$param}", value.toString())
      }
    }

    val result = routeResponseCache.getSuspending(rewrittenTargetRoute) {
      fetchBrawlsApi<T>(rewrittenTargetRoute)
    } as T

    call.respondJson(result)
  }
}

fun Application.configureRouting() = routing {
  brawlsApi<ChatStatistics>("chat_statistics", "chat_statistics")
  brawlsApi<ChatStatistics>("chat_statistics/{uuid}", "chat_statistics/{uuid}", "uuid")
  brawlsApi<PlayerProfile>("profile/{uuid}", "profile/{uuid}", "uuid")
  brawlsApi<Leaderboard>("leaderboard/{event}/{game?}/{limit?}/{offset?}/{type?}", "stats/{event}/{game?}/{limit?}/{offset?}/{type?}", "event", "game?", "limit?", "offset?", "type?")

  configurePlayTimeStats()
}
