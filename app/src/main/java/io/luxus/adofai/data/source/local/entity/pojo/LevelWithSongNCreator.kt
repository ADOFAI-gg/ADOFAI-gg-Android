package io.luxus.adofai.data.source.local.entity.pojo

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import io.luxus.adofai.data.source.local.entity.LocalLevel
import io.luxus.adofai.data.source.local.entity.LocalPerson
import io.luxus.adofai.data.source.local.entity.LocalSong
import io.luxus.adofai.data.source.local.entity.relation.LevelCreatorCrossRef

data class LevelWithSongNCreator(
    @Embedded val localLevel: LocalLevel,
    @Relation(
        entity = LocalSong::class,
        parentColumn = "levelId",
        entityColumn = "songId"
    ) val songWithArtist: SongWithArtist,
    @Relation(
        entity = LocalPerson::class,
        parentColumn = "levelId",
        entityColumn = "personId",
        associateBy = Junction(LevelCreatorCrossRef::class)
    ) val creator: List<LocalPerson>
)
