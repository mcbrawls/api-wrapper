@file:OptIn(ExperimentalUuidApi::class)

package net.mcbrawls.api.wrapper

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.Url
import io.ktor.serialization.kotlinx.json.json
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

/** An API client for the Brawls API. */
public open class BrawlsAPI(
  /** The server URL. */
  public val serverUrl: Url,
) {
  public companion object {
    /** A local instance of the Brawls API wrapper. */
    public val Local: BrawlsAPI = BrawlsAPI(Url("http://localhost:8080"))
    public val Default: BrawlsAPI = BrawlsAPI(Url("https://api.brawls-stats.radsteve.net"))
  }

  @PublishedApi
  internal val httpClient: HttpClient = HttpClient(CIO) {
    install(ContentNegotiation) {
      json()
    }
  }

  /** Fetches the global chat statistics of Brawls. */
  public suspend fun globalChatStatistics(): ChatStatistics {
    return fetch("chat_statistics")
  }

  /** Fetches chat statistics for the given [player]. */
  public suspend fun chatStatistics(player: Uuid): ChatStatistics {
    return fetch("chat_statistics/$player")
  }

  /** Fetches a leaderboard of the given [statisticEvent]. */
  public suspend fun leaderboard(
    statisticEvent: StatisticEvent,
    game: BrawlsGame? = null,
    type: Leaderboard.Type? = null,
    limit: Int? = null,
    offset: Int? = null,
  ): Leaderboard {
    if (offset != null) {
      requireNotNull(limit) { "offset requires a limit" }

      require(offset >= 0)
    }

    if (limit != null) {
      require(limit >= 0)
    }

    return fetch(
      buildString {
        append("leaderboard/")
        append(statisticEvent.id)
        if (game != null) {
          append('/')
          append(game.id)
        }
        if (limit != null) {
          append('/')
          append(limit)
        }
        if (offset != null) {
          append('/')
          append(offset)
        }
        if (type != null) {
          append('/')
          append(type.id)
        }
      },
    )
  }

  /** Fetches play time stats for the given [player]. */
  public suspend fun playTimeStats(player: Uuid): PlayTimeStats {
    return fetch("playtime/$player")
  }

  /** Fetches the profile for the given [player]. */
  public suspend fun profile(player: Uuid): PlayerProfile {
    return fetch("profile/$player")
  }
}
