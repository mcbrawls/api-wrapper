@file:OptIn(ExperimentalUuidApi::class)

package net.mcbrawls.api.wrapper.server

import com.github.benmanes.caffeine.cache.Cache
import com.github.benmanes.caffeine.cache.Caffeine
import io.ktor.http.HttpStatusCode
import io.ktor.server.response.respond
import io.ktor.server.routing.Routing
import io.ktor.server.routing.get
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import net.mcbrawls.api.wrapper.PlaySession
import net.mcbrawls.api.wrapper.PlayTimeStats
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import kotlin.time.Duration
import kotlin.time.Duration.Companion.hours
import kotlin.time.measureTime
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

private var sessions: List<PlaySession> = emptyList()
val playStatCache: Cache<Uuid, PlayTimeStats> = Caffeine.newBuilder().build()

private suspend fun fetchSessions(): List<PlaySession> {
  return fetchBrawlsApi<List<PlaySession>>("sessions").also { result ->
    sessions = result
  }
}

private suspend fun computeStats() = measureTime {
  val sessions = fetchSessions()
  val playerSessions = sessions.groupBy(PlaySession::player)
  val playerStats = playerSessions.entries.associate { (uuid, sessions) ->
    val time = sessions.fold(Duration.ZERO) { acc, elem ->
      acc + (elem.end - elem.start)
    }
    val gamesPlayed = sessions.sumOf(PlaySession::gamesPlayed)

    uuid to PlayTimeStats(time.inWholeMilliseconds, gamesPlayed, uuid)
  }

  playerStats.forEach { uuid, stats ->
    playStatCache.put(uuid, stats)
  }
}

private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO.limitedParallelism(1))
private val logger: Logger = LoggerFactory.getLogger("PlayTimeStats")

private suspend fun initialiseStats() {
  logger.info("Computing stats...")
  val took = computeStats()
  logger.info("Took $took")

  coroutineScope.launch {
    while (true) {
      delay(1.hours)

      logger.info("Computing stats...")
      val took = computeStats()
      logger.info("Took $took")
    }
  }
}

private fun playStats(uuid: Uuid): PlayTimeStats {
  return playStatCache.get(uuid) { uuid -> PlayTimeStats(0, 0, uuid) }
}

fun Routing.configurePlayTimeStats() {
  coroutineScope.launch {
    initialiseStats()
  }

  get("/sessions") {
    call.respondJson(sessions)
  }

  get("/playtime/{uuid}") {
    val stringifiedUuid = call.parameters["uuid"]?.toString()
    if (stringifiedUuid == null) {
      call.respond(HttpStatusCode.BadRequest, "missing parameter 'uuid'")
      return@get
    }
    val uuid = runCatching { Uuid.parse(stringifiedUuid) }.getOrElse {
      call.respond(HttpStatusCode.BadRequest, "invalid uuid")
      return@get
    }

    call.respondJson(playStats(uuid))
  }
}
