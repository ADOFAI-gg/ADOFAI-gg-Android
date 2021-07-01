package io.luxus.adofai.data.source.local.entity.relation

import androidx.room.Entity
import androidx.room.Index

@Entity(
    tableName = "level_tag_cross_ref",
    inheritSuperIndices = true,
    primaryKeys = ["levelId", "tagId"],
    indices = [
        Index(value = ["levelId"]),
        Index(value = ["tagId"]),
    ]
)
data class LevelTagCrossRef(
    val levelId: Long,
    val tagId: Long,
)
