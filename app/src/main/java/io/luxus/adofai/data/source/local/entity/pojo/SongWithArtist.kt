package io.luxus.adofai.data.source.local.entity.pojo

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import io.luxus.adofai.data.source.local.entity.LocalPerson
import io.luxus.adofai.data.source.local.entity.LocalSong
import io.luxus.adofai.data.source.local.entity.relation.SongArtistCrossRef

data class SongWithArtist(
    @Embedded val localSong: LocalSong,
    @Relation(
        entity = LocalPerson::class,
        parentColumn = "songId",
        entityColumn = "personId",
        associateBy = Junction(SongArtistCrossRef::class)
    )
    val artists: List<LocalPerson>
)