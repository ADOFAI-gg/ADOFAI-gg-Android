package io.luxus.adofai.domain.entity

data class Song(
    val name: String,
    val minBpm: Double,
    val maxBpm: Double,
    val artists: List<Person>
)
