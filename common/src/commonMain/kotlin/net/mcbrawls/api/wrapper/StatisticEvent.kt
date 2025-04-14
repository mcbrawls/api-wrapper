package net.mcbrawls.api.wrapper

/** Represents an event that causes a statistic to increase. */
public enum class StatisticEvent(
  /** The internal ID of this event. */
  public val id: String,

  /** The displayed name of the event. */
  public val displayName: String,
) {
  Participation("participation", "Game Participation"),

  Placement("placement", "Placement"),
  NoPlacement("no_placement", "No Placement"),

  Assist("kill_assist", "Assisted Elimination"),
  Kill("kill", "Elimination"),
  FallKill("kill_fall", "Fall Kill"),
  VoidKill("kill_void", "Void Kill"),
  MovingKill("kill_moving", "Moving Kill"),
  InvisibleKill("kill_invisible", "Invisible Kill"),
  SnipeKill("kill_snipe", "Sniper Kill"),
  RiptideKill("kill_by_riptide", "Riptide Kill"),
  DirectRiptideKill("kill_direct_riptide", "Direct Riptide Kill"),
  StreakKill("kill_within_streak", "Streak Kill"),
  KillStreakFinish("kill_streak", "Kill Streak Finish"),
  SpleefKill("spleef", "Spleef"),
  SnowballKill("direct_snowball_kill", "Snowball Kill"),

  Death("death", "Death"),
  Outlive("outlive", "Outlived Player"),

  Win("win", "Win"),
  RoundWin("round_win", "Round Win"),
  Loss("loss", "Loss"),
  RoundLoss("round_loss", "Round Loss"),

  /** A team sweep **during Dodgebolt**. Legacy. Use [TeamSweep] for all other games. */
  DodgeboltTeamSweep("dodgebolt_team_sweep", "Sweep"),
  TeamSweep("team_sweep", "Sweep"),
  ReverseTeamSweep("reverse_team_sweep", "Reverse Sweep"),

  PowderFloors("powder_floors", "Floor Drop Survived"),
  RocketHit("rocket_hit", "Direct Rocket Hit"),

  ArrowFired("arrow_fired", "Arrow Fired"),
  ArrowHit("arrow_hit", "Arrow Hit"),
  SnowballHit("snowball_hit", "Snowball Hit"),
  SnowballBlockBreak("snowball_block_break", "Block broken with Snowball"),

  Fall("fall", "Fall"),
  ItemPickup("item_entity_pickup", "Picked Up Item"),
  Capture("capture", "Capture"),
  CaptureLost("capture_lost", "Capture Lost"),
  CaptureCollected("capture_collected", "Capture Collected"),
  CaptureCollectPrevented("capture_collect_prevented", "Capture Collect Prevented"),
}
