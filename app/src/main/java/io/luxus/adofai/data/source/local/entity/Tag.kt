package io.luxus.adofai.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "tag",
    inheritSuperIndices = true
)
data class Tag(
    @PrimaryKey val tagId: Long,
    val name: String,
    val priority: Int
)