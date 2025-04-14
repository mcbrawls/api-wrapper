package net.mcbrawls.api.wrapper

import kotlinx.serialization.Serializable

/** Ranks on the Brawls server. */
@Serializable
public enum class BrawlsRank {
  ADMIN,
  BUILDER,
  MODERATOR,
  MCCIT,
  PARTNER,
  STAFF,
  DEFAULT,
}
