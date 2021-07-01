package io.luxus.adofai.data.source.local.dao

import androidx.room.*
import io.luxus.adofai.data.source.local.entity.*
import io.luxus.adofai.data.source.local.entity.relation.LevelCreatorCrossRef
import io.luxus.adofai.data.source.local.entity.relation.LevelTagCrossRef
import io.luxus.adofai.data.source.local.entity.relation.SongArtistCrossRef

@Dao
abstract class InitializeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertLog(initializeLog: InitializeLog)

    @Query("SELECT initialize_log.`data` FROM initialize_log WHERE `key`=:key;")
    abstract fun getLog(key: String): String?

    @Transaction
    open fun initDatabase(
        localPersonList: Collection<LocalPerson>, localSongList: Collection<LocalSong>,
        localTagList: Collection<LocalTag>,
        localLevelList: Collection<LocalLevel>, localPlayLogList: Collection<LocalPlayLog>,
        songArtistCrossRefList: Collection<SongArtistCrossRef>,
        levelTagCrossRefList: Collection<LevelTagCrossRef>,
        levelCreatorCrossRefList: Collection<LevelCreatorCrossRef>,
        initializeLog: InitializeLog) {
        clearDatabase()
        insertPerson(localPersonList)
        insertSong(localSongList)
        insertTag(localTagList)
        insertLevel(localLevelList)
        insertPlayLog(localPlayLogList)
        insertSongArtistCrossRef(songArtistCrossRefList)
        insertLevelTagCrossRef(levelTagCrossRefList)
        insertLevelCreatorCrossRef(levelCreatorCrossRefList)
        insertLog(initializeLog)
    }

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertPerson(localPersonList: Collection<LocalPerson>)

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertSong(localSongList: Collection<LocalSong>)

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertTag(localTagList: Collection<LocalTag>)

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertLevel(localLevelList: Collection<LocalLevel>)

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertPlayLog(localPlayLogList: Collection<LocalPlayLog>)

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertSongArtistCrossRef(songArtistCrossRefList: Collection<SongArtistCrossRef>)

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertLevelTagCrossRef(levelTagCrossRefList: Collection<LevelTagCrossRef>)

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertLevelCreatorCrossRef(levelCreatorCrossRefList: Collection<LevelCreatorCrossRef>)

    @Transaction
    open fun clearDatabase() {
        clearLevelCreatorCrossRef()
        clearLevelTagCrossRef()
        clearSongArtistCrossRef()
        clearPlayLog()
        clearLevel()
        clearTag()
        clearSong()
        clearPerson()
    }

    @Query("DELETE FROM level_creator_cross_ref")
    abstract fun clearLevelCreatorCrossRef()

    @Query("DELETE FROM level_tag_cross_ref")
    abstract fun clearLevelTagCrossRef()

    @Query("DELETE FROM song_artist_cross_ref")
    abstract fun clearSongArtistCrossRef()

    @Query("DELETE FROM play_log")
    abstract fun clearPlayLog()

    @Query("DELETE FROM localLevel")
    abstract fun clearLevel()

    @Query("DELETE FROM tag")
    abstract fun clearTag()

    @Query("DELETE FROM localSong")
    abstract fun clearSong()

    @Query("DELETE FROM person")
    abstract fun clearPerson()


}