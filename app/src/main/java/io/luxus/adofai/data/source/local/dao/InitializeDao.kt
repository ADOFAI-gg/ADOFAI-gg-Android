package io.luxus.adofai.data.source.local.dao

import androidx.room.*
import io.luxus.adofai.data.source.local.entity.*
import io.luxus.adofai.data.source.local.entity.relation.LevelCreatorCrossRef
import io.luxus.adofai.data.source.local.entity.relation.LevelTagCrossRef
import io.luxus.adofai.data.source.local.entity.relation.SongArtistCrossRef
import java.util.*

@Dao
abstract class InitializeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertLog(initializeLog: InitializeLog)

    @Query("SELECT initialize_log.`data` FROM initialize_log WHERE `key`=:key;")
    abstract fun getLog(key: String): String?

    @Transaction
    open fun initDatabase(
        personList: Collection<Person>, songList: Collection<Song>,
        tagList: Collection<Tag>,
        levelList: Collection<Level>, playLogList: Collection<PlayLog>,
        songArtistCrossRefList: Collection<SongArtistCrossRef>,
        levelTagCrossRefList: Collection<LevelTagCrossRef>,
        levelCreatorCrossRefList: Collection<LevelCreatorCrossRef>,
        initializeLog: InitializeLog) {
        clearDatabase()
        insertPerson(personList)
        insertSong(songList)
        insertTag(tagList)
        insertLevel(levelList)
        insertPlayLog(playLogList)
        insertSongArtistCrossRef(songArtistCrossRefList)
        insertLevelTagCrossRef(levelTagCrossRefList)
        insertLevelCreatorCrossRef(levelCreatorCrossRefList)
        insertLog(initializeLog)
    }

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertPerson(personList: Collection<Person>)

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertSong(songList: Collection<Song>)

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertTag(tagList: Collection<Tag>)

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertLevel(levelList: Collection<Level>)

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertPlayLog(playLogList: Collection<PlayLog>)

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

    @Query("DELETE FROM level")
    abstract fun clearLevel()

    @Query("DELETE FROM tag")
    abstract fun clearTag()

    @Query("DELETE FROM song")
    abstract fun clearSong()

    @Query("DELETE FROM person")
    abstract fun clearPerson()


}