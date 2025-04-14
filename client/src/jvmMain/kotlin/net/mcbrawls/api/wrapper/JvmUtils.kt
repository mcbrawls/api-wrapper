@file:OptIn(ExperimentalUuidApi::class)

package net.mcbrawls.api.wrapper

import java.util.UUID
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

/** Converts this UUID to a java UUID. */
public inline val Uuid.java: UUID get() = toLongs(::UUID)

/** Converts this UUID to a kotlin UUID. */
public inline val UUID.kotlin: Uuid get() = Uuid.fromLongs(mostSignificantBits, leastSignificantBits)
