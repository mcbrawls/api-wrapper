@file:OptIn(ExperimentalUuidApi::class)

package net.mcbrawls.api.wrapper

import kotlinx.serialization.Serializable
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

/** Represents an entry on a [Leaderboard]. */
@Serializable
public data class LeaderboardEntry(
  /** The position of the player on the leaderboard. */
  public val position: Int,

  /** The UUID of the player. */
  public val uuid: Uuid,

  /** The leaderboard entry value. */
  public val value: Int,
)
