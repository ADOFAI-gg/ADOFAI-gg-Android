package io.luxus.adofai.data.source.local.entity.relation

import androidx.room.Entity
import androidx.room.Index

@Entity(
    tableName = "level_tag_cross_ref",
    inheritSuperIndices = true,
    primaryKeys = ["song", "artist"],
    indices = [
        Index(value = ["song"]),
        Index(value = ["artist"]),
    ]
)
data class LevelTagCrossRef(
    val level: Long,
    val tag: Long,
)
