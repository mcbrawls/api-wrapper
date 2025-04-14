@file:OptIn(ExperimentalUuidApi::class)

package net.mcbrawls.api.wrapper

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

/** Represents a player's profile. */
public data class PlayerProfile(
  /** The experience of this player. */
  public val experience: Int,

  /** The rank of this player. */
  public val rank: BrawlsRank,

  /** The UUID of this player. */
  public val uuid: Uuid,
)
