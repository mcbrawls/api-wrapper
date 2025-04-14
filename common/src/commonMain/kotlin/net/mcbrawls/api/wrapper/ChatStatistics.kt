package net.mcbrawls.api.wrapper

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/** Chat message statistics for a player. */
@Serializable
public data class ChatStatistics(
  /** The amount of filtered messages this player has sent. */
  @SerialName("filtered")
  public val filteredMessages: Int,

  /** The amount of messages this player has sent in a local chat. */
  @SerialName("local")
  public val localMessages: Int,
)
