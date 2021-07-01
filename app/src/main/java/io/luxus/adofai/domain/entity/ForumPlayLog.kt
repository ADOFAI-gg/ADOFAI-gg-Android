package io.luxus.adofai.domain.entity

import java.sql.Date

data class ForumPlayLog(
    val id: Long,
    val timeStamp: Date,
    val name: String,
    val userCode: Long,
    val mapId: Long,
    val song: String,
    val artist: List<String>,
    val creator: List<String>,
    val level: Double,
    val tiles: Long,
    val ra: Double,
    val accuracy: Double,
    val speed: Long,
    val pp: Double,
    val localRank: Long,
    val totalRank: Long,
    val weighted: Double,
    val url: String
)
