package io.luxus.adofai.data.source.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.sql.Date

@Entity(
    tableName = "play_log",
    inheritSuperIndices = true,
    indices = [Index(value = ["player"]), Index(value = ["level"])],
    foreignKeys = [
        ForeignKey(
            entity = Person::class,
            parentColumns = ["id"],
            childColumns = ["player"]
        ),
        ForeignKey(
            entity = Level::class,
            parentColumns = ["id"],
            childColumns = ["level"]
        ),
    ]
)
data class PlayLog(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val player: Long,
    val level: Long,
    val time: Date,
    val speed: Long,
    val accuracy: Double,
    val playPoint: Double,
    val url: String
)