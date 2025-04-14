package net.mcbrawls.api.wrapper

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/** Represents a leaderboard on the server. */
@Serializable
public data class Leaderboard(
  /** The id of the leaderboard. */
  public val id: String,

  /** The displayed name of the leaderboard. */
  @SerialName("title")
  public val displayName: String,

  /** The entries of this leaderboard. */
  public val entries: List<LeaderboardEntry>,
) {
  /** The type of values shown in the entries. Defaults to [EventCount]. */
  public enum class Type(
    /** The internal id of this type. */
    public val id: String,
  ) {
    ExperienceSum("EXPERIENCE_SUM"),
    EventCount("EVENT_COUNT"),
  }
}
