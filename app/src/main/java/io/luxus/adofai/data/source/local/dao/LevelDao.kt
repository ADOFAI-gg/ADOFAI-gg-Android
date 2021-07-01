package io.luxus.adofai.data.source.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import io.luxus.adofai.data.source.local.entity.pojo.LevelWithSongNCreator
import io.luxus.adofai.data.source.local.entity.pojo.SongWithArtist

@Dao
abstract class LevelDao {

    // need function
    // order by id (asc / desc)
    // order by localLevel (asc / desc)
    // order by creator name (asc / desc)
    // order by localSong name (asc / desc)
    // order by artist (asc / desc)
    // order by max / min bpm (asc / desc)
    // order by tile amount (asc / desc)

    @Transaction
    @Query("SELECT * FROM localLevel WHERE levelId = :id;")
    abstract fun getLevelWithSongNCreator(id: Long): LevelWithSongNCreator

    @Transaction
    @Query("SELECT * FROM localLevel ORDER BY levelId ASC;")
    abstract fun getOrderByIdAsc(): LevelWithSongNCreator

    @Transaction
    @Query("SELECT * FROM localLevel ORDER BY levelId DESC;")
    abstract fun getOrderByIdDesc(): LevelWithSongNCreator

    @Transaction
    @Query("SELECT * FROM localLevel ORDER BY level ASC;")
    abstract fun getOrderByLevelAsc(): LevelWithSongNCreator

    @Transaction
    @Query("SELECT * FROM localLevel ORDER BY level DESC;")
    abstract fun getOrderByLevelDesc(): LevelWithSongNCreator

    @Transaction
    @Query("SELECT * FROM localSong WHERE songId = :id;")
    abstract fun getSongWithArtist(id: Long): SongWithArtist

}