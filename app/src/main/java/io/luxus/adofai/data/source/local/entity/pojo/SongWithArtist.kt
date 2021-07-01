package io.luxus.adofai.data.source.local.entity.pojo

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import io.luxus.adofai.data.source.local.entity.Person
import io.luxus.adofai.data.source.local.entity.Song
import io.luxus.adofai.data.source.local.entity.relation.SongArtistCrossRef

data class SongWithArtist(
    @Embedded val song: Song,
    @Relation(
        entity = Person::class,
        parentColumn = "songId",
        entityColumn = "personId",
        associateBy = Junction(SongArtistCrossRef::class)
    )
    val artists: List<Person>
)