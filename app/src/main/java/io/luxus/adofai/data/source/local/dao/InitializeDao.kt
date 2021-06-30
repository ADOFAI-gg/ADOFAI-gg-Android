package io.luxus.adofai.data.source.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction

@Dao
abstract class InitializeDao {

    @Transaction
    open fun initDatabase() {
        clearDatabase()

    }





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