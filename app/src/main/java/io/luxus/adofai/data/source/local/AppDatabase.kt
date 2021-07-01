package io.luxus.adofai.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import io.luxus.adofai.data.source.local.converter.DateConverter
import io.luxus.adofai.data.source.local.dao.InitializeDao
import io.luxus.adofai.data.source.local.dao.LevelDao
import io.luxus.adofai.data.source.local.entity.*
import io.luxus.adofai.data.source.local.entity.relation.LevelCreatorCrossRef
import io.luxus.adofai.data.source.local.entity.relation.LevelTagCrossRef
import io.luxus.adofai.data.source.local.entity.relation.SongArtistCrossRef

@Database(entities = [

    InitializeLog::class,
    LocalLevel::class,
    LocalPerson::class,
    LocalPlayLog::class,
    LocalSong::class,
    LocalTag::class,

    LevelCreatorCrossRef::class,
    LevelTagCrossRef::class,
    SongArtistCrossRef::class,

], version = 1, exportSchema = false)
@TypeConverters(value = [
    DateConverter::class,
])
abstract class AppDatabase : RoomDatabase() {

    abstract fun initializeDao(): InitializeDao

    abstract fun levelDao(): LevelDao

}