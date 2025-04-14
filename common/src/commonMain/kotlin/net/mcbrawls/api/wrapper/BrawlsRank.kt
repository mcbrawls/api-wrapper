package net.mcbrawls.api.wrapper

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/** Ranks on the Brawls server. */
@Serializable
public enum class BrawlsRank {
  @SerialName("ADMIN")
  Admin,

  @SerialName("BUILDER")
  Builder,

  @SerialName("MODERATOR")
  Moderator,

  @SerialName("MCCIT")
  McciTournaments,

  @SerialName("PARTNER")
  Partner,

  @SerialName("STAFF")
  Staff,

  @SerialName("DEFAULT")
  Player;
}
