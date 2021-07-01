package io.luxus.adofai.data.source.local.entity.relation

import androidx.room.Entity
import androidx.room.Index

@Entity(
    tableName = "song_artist_cross_ref",
    inheritSuperIndices = true,
    primaryKeys = ["songId", "personId"],
    indices = [
        Index(value = ["songId"]),
        Index(value = ["personId"]),
    ]
)
data class SongArtistCrossRef(
    val songId: Long,
    val personId: Long,
)