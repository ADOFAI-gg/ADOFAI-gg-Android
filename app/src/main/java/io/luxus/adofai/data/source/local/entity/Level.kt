package io.luxus.adofai.data.source.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "level",
    inheritSuperIndices = true,
    indices = [Index(value = ["song"])],
    foreignKeys = [
        ForeignKey(
            entity = Song::class,
            parentColumns = ["id"],
            childColumns = ["song"]
        ),
    ]
)
data class Level(
    @PrimaryKey val id: Long,
    val song: Long,
    val level: Double,
    val tile: Long,
    val epilepsyWarning: Boolean,
    val video: String,
    val download: String,
    val workshop: String?
)
