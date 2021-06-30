package io.luxus.adofai.domain.entity

data class ForumLevel(
    val id: Long,
    val song: String,
    val artist: List<String>,
    val level: Double,
    val creator: List<String>,
    val download: String?,
    val workshop: String?,
    val video: String?,
    val epilepsyWarning: Boolean,
    val minBpm: Double?,
    val maxBpm: Double?,
    val tiles: Long?,
    val tags: List<String>
)
