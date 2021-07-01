package io.luxus.adofai.domain.entity

data class Level(
    val song: Song,
    val creators: List<Person>,
    val tags: List<Tag>,

    val level: Double,
    val tile: Long,
    val epilepsy_warning: Boolean,
    val video: String,
    val download: String,
    val workshop: String,
)
