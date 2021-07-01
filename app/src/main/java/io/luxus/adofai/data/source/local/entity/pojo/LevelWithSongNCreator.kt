package io.luxus.adofai.data.source.local.entity.pojo

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import io.luxus.adofai.data.source.local.entity.Level
import io.luxus.adofai.data.source.local.entity.Person
import io.luxus.adofai.data.source.local.entity.Song
import io.luxus.adofai.data.source.local.entity.relation.LevelCreatorCrossRef

data class LevelWithSongNCreator(
    @Embedded val level: Level,
    @Relation(
        entity = Song::class,
        parentColumn = "levelId",
        entityColumn = "songId"
    ) val songWithArtist: SongWithArtist,
    @Relation(
        entity = Person::class,
        parentColumn = "levelId",
        entityColumn = "personId",
        associateBy = Junction(LevelCreatorCrossRef::class)
    ) val creator: List<Person>
)
