package io.luxus.adofai.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "song",
    inheritSuperIndices = true
)
data class LocalSong(
    @PrimaryKey val songId: Long,
    val name: String,
    val minBpm: Double,
    val maxBpm: Double
)