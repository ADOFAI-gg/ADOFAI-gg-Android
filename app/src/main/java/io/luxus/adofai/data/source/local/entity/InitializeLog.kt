package io.luxus.adofai.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "initialize_log",
    inheritSuperIndices = true
)
data class InitializeLog(
    @PrimaryKey val key: String,
    val data: String
)
