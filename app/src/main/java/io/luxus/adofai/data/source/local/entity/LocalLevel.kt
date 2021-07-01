package io.luxus.adofai.data.source.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "localLevel",
    inheritSuperIndices = true,
    indices = [Index(value = ["song"])],
    foreignKeys = [
        ForeignKey(
            entity = LocalSong::class,
            parentColumns = ["songId"],
            childColumns = ["song"]
        ),
    ]
)
data class LocalLevel(
    @PrimaryKey val levelId: Long,
    val song: Long,
    val level: Double,
    val tile: Long,
    val epilepsyWarning: Boolean,
    val video: String,
    val download: String,
    val workshop: String?
)
