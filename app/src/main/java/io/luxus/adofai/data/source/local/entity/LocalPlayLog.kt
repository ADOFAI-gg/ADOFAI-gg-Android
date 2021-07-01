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
            entity = LocalPerson::class,
            parentColumns = ["personId"],
            childColumns = ["player"]
        ),
        ForeignKey(
            entity = LocalLevel::class,
            parentColumns = ["levelId"],
            childColumns = ["level"]
        ),
    ]
)
data class LocalPlayLog(
    @PrimaryKey(autoGenerate = true) val playLogId: Long,
    val player: Long,
    val level: Long,
    val time: Date,
    val speed: Long,
    val accuracy: Double,
    val playPoint: Double,
    val url: String
)