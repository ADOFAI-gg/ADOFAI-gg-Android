package io.luxus.adofai.domain.entity

data class Level(
    val id: Long,
    val song: Song,
    val creators: List<Person>,
    val tags: List<Tag>,

    val level: Double,
    val tile: Long,
    val epilepsyWarning: Boolean,
    val video: String,
    val download: String,
    val workshop: String,
)
