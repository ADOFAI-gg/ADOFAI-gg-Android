package io.luxus.adofai.data.source.local.entity.relation

import androidx.room.Entity
import androidx.room.Index

@Entity(
    tableName = "level_creator_cross_ref",
    inheritSuperIndices = true,
    primaryKeys = ["level", "creator"],
    indices = [
        Index(value = ["level"]),
        Index(value = ["creator"]),
    ]
)
data class LevelCreatorCrossRef(
    val level: Long,
    val creator: Long
)
