package net.mcbrawls.api.wrapper

public enum class BrawlsGame(
  /** The internal id of this game. */
  public val id: String,

  /** The displayed name of this game. */
  public val displayName: String,
) {
  Dodgebolt("dodgebolt", "Dodgebolt"),
  RocketSpleef("rocket_spleef", "Rocket Spleef"),
  Rise("old_rise", "Rise"),
  RiseCapture("rise_capture", "Rise: Capture"),
  Spleef("spleef", "Spleef"),
  OneShot("one_shot", "One Shot"),
}
