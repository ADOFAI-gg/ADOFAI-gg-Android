package io.luxus.adofai.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "person",
    inheritSuperIndices = true
)
data class Person(
    @PrimaryKey val id: Long,
    val name: String,
    val discord: String?
)
