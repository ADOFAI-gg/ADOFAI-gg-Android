package io.luxus.adofai.domain.entity

import java.sql.Date

data class PlayLog(
    val player: Person,
    val level: Level,
    val time: Date,
    val speed: Long,
    val accuracy: Double,
    val playPoint: Double,
    val url: String
)