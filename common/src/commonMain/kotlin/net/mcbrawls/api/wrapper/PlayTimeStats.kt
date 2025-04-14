@file:OptIn(ExperimentalUuidApi::class)

package net.mcbrawls.api.wrapper

import kotlinx.serialization.Serializable
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

/** Statistics about a player's play time. */
@Serializable
public data class PlayTimeStats(
  /** The play time of the player in milliseconds. */
  public val playTimeMillis: Long,

  /** The amount of games played. */
  public val gamesPlayed: Int,

  /** The player's UUID. */
  public val player: Uuid,
) {
  /** The play time of the player. */
  public val playTime: Duration = playTimeMillis.milliseconds
}
