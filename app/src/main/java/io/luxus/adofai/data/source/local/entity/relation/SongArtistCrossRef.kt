package io.luxus.adofai.data.source.local.entity.relation

import androidx.room.Entity
import androidx.room.Index

@Entity(
    tableName = "song_artist_cross_ref",
    inheritSuperIndices = true,
    primaryKeys = ["song", "artist"],
    indices = [
        Index(value = ["song"]),
        Index(value = ["artist"]),
    ]
)
data class SongArtistCrossRef(
    val song: Long,
    val artist: Long,
)