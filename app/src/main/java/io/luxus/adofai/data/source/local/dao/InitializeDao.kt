package io.luxus.adofai.data.source.local.dao

import androidx.room.*
import io.luxus.adofai.data.source.local.entity.*
import io.luxus.adofai.data.source.local.entity.relation.LevelCreatorCrossRef
import io.luxus.adofai.data.source.local.entity.relation.LevelTagCrossRef
import io.luxus.adofai.data.source.local.entity.relation.SongArtistCrossRef

@Dao
abstract class InitializeDao {

    @Transaction
    open fun initDatabase(personList: List<Person>, songList: List<Song>, tagList: List<Tag>,
                          levelList: List<Level>, playLogList: List<PlayLog>,
                          songArtistCrossRefList: List<SongArtistCrossRef>,
                          levelTagCrossRefList: List<LevelTagCrossRef>,
                          levelCreatorCrossRefList: List<LevelCreatorCrossRef>) {
        clearDatabase()
        insertPerson(personList)
        insertSong(songList)
        insertTag(tagList)
        insertLevel(levelList)
        insertPlayLog(playLogList)
        insertSongArtistCrossRef(songArtistCrossRefList)
        insertLevelTagCrossRef(levelTagCrossRefList)
        insertLevelCreatorCrossRef(levelCreatorCrossRefList)
    }

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertPerson(personList: List<Person>)

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertSong(songList: List<Song>)

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertTag(tagList: List<Tag>)

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertLevel(levelList: List<Level>)

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertPlayLog(playLogList: List<PlayLog>)

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertSongArtistCrossRef(songArtistCrossRefList: List<SongArtistCrossRef>)

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertLevelTagCrossRef(levelTagCrossRefList: List<LevelTagCrossRef>)

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertLevelCreatorCrossRef(levelCreatorCrossRefList: List<LevelCreatorCrossRef>)

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