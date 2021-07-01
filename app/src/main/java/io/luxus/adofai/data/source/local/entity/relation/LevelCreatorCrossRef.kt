package io.luxus.adofai.data.source.local.entity.relation

import androidx.room.Entity
import androidx.room.Index

@Entity(
    tableName = "level_creator_cross_ref",
    inheritSuperIndices = true,
    primaryKeys = ["levelId", "personId"],
    indices = [
        Index(value = ["levelId"]),
        Index(value = ["personId"]),
    ]
)
data class LevelCreatorCrossRef(
    val levelId: Long,
    val personId: Long
)
