package io.luxus.adofai.data.source.local.entity.pojo

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import io.luxus.adofai.data.source.local.entity.LocalLevel
import io.luxus.adofai.data.source.local.entity.LocalPerson
import io.luxus.adofai.data.source.local.entity.LocalSong
import io.luxus.adofai.data.source.local.entity.LocalTag
import io.luxus.adofai.data.source.local.entity.relation.LevelCreatorCrossRef
import io.luxus.adofai.data.source.local.entity.relation.LevelTagCrossRef

data class LevelWithSongNCreator(
    @Embedded val localLevel: LocalLevel,
    @Relation(
        entity = LocalSong::class,
        parentColumn = "song",
        entityColumn = "songId"
    ) val songWithArtist: SongWithArtist,
    @Relation(
        entity = LocalPerson::class,
        parentColumn = "levelId",
        entityColumn = "personId",
        associateBy = Junction(LevelCreatorCrossRef::class)
    ) val creators: List<LocalPerson>,
    @Relation(
        entity = LocalTag::class,
        parentColumn = "levelId",
        entityColumn = "tagId",
        associateBy = Junction(LevelTagCrossRef::class)
    ) val tags: List<LocalTag>
)
