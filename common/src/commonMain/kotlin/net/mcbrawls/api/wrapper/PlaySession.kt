@file:OptIn(ExperimentalUuidApi::class)

package net.mcbrawls.api.wrapper

import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

/** A session that somebody played on the server for a period of time. */
@Serializable
public data class PlaySession(
  /** The amounts of games played in this session. */
  public val gamesPlayed: Int,

  /** The UUID of this player. */
  @SerialName("uuid")
  public val player: Uuid,

  /** The start of this session. */
  public val start: Instant,

  /** The end of this session. */
  public val end: Instant,
)
