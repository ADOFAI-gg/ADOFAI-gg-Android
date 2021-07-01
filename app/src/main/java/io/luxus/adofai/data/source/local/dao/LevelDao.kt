package io.luxus.adofai.data.source.local.dao

import androidx.room.Dao
import androidx.room.Query
import io.luxus.adofai.data.source.local.entity.pojo.LevelWithSongNCreator
import io.luxus.adofai.data.source.local.entity.pojo.SongWithArtist

@Dao
abstract class LevelDao {

    // need function
    // order by id (asc / desc)
    // order by level (asc / desc)
    // order by creator name (asc / desc)
    // order by song name (asc / desc)
    // order by artist (asc / desc)
    // order by max / min bpm (asc / desc)
    // order by tile amount (asc / desc)

    @Query("SELECT * FROM level WHERE levelId = :id;")
    abstract fun getLevelWithSongNCreator(id: Long): LevelWithSongNCreator

    @Query("SELECT * FROM level ORDER BY levelId ASC;")
    abstract fun getOrderByIdAsc(): LevelWithSongNCreator

    @Query("SELECT * FROM level ORDER BY levelId DESC;")
    abstract fun getOrderByIdDesc(): LevelWithSongNCreator

    @Query("SELECT * FROM level ORDER BY level ASC;")
    abstract fun getOrderByLevelAsc(): LevelWithSongNCreator

    @Query("SELECT * FROM level ORDER BY level DESC;")
    abstract fun getOrderByLevelDesc(): LevelWithSongNCreator

    @Query("SELECT * FROM song WHERE songId = :id;")
    abstract fun getSongWithArtist(id: Long): SongWithArtist

}