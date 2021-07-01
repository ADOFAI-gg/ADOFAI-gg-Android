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
    // order by level (asc / desc)
    // order by artist (asc / desc)
    // order by creator name (asc / desc)
    // order by song name (asc / desc)
    // order by max / min bpm (asc / desc)
    // order by tile amount (asc / desc)

    @Transaction
    @Query("SELECT * FROM level WHERE levelId = :id;")
    abstract fun getLevelWithSongNCreator(id: Long): LevelWithSongNCreator

    @Transaction
    @Query("SELECT * FROM level ORDER BY levelId ASC;")
    abstract fun getOrderByIdAsc(): List<LevelWithSongNCreator>

    @Transaction
    @Query("SELECT * FROM level ORDER BY levelId DESC;")
    abstract fun getOrderByIdDesc(): List<LevelWithSongNCreator>

    @Transaction
    @Query("SELECT * FROM level ORDER BY level ASC;")
    abstract fun getOrderByLevelAsc(): List<LevelWithSongNCreator>

    @Transaction
    @Query("SELECT * FROM level ORDER BY level DESC;")
    abstract fun getOrderByLevelDesc(): List<LevelWithSongNCreator>

    @Transaction
    @Query("""
        SELECT * FROM level AS l
            LEFT JOIN song_artist_cross_ref AS sa ON sa.songId=l.song
            GROUP BY l.song
            ORDER BY (SELECT name FROM person WHERE person.personId=sa.personId) ASC
            """)
    abstract fun getOrderByArtistAsc(): List<LevelWithSongNCreator>

    @Transaction
    @Query("""
        SELECT * FROM level AS l
            LEFT JOIN song_artist_cross_ref AS sa ON sa.songId=l.song
            GROUP BY l.song
            ORDER BY (SELECT name FROM person WHERE person.personId=sa.personId) DESC
            """)
    abstract fun getOrderByArtistDesc(): List<LevelWithSongNCreator>

    @Transaction
    @Query("""
        SELECT * FROM level AS l
            LEFT JOIN level_creator_cross_ref AS lc ON lc.levelId=l.levelId
            GROUP BY l.levelId
            ORDER BY (SELECT name FROM person WHERE person.personId=lc.personId) ASC
            """)
    abstract fun getOrderByCreatorAsc(): List<LevelWithSongNCreator>

    @Transaction
    @Query("""
        SELECT * FROM level AS l
            LEFT JOIN level_creator_cross_ref AS lc ON lc.levelId=l.levelId
            GROUP BY l.levelId
            ORDER BY (SELECT name FROM person WHERE person.personId=lc.personId) DESC
            """)
    abstract fun getOrderByCreatorDesc(): List<LevelWithSongNCreator>

    @Transaction
    @Query("""
        SELECT * FROM level AS l
            ORDER BY (SELECT name FROM song WHERE song.songId=l.song) ASC
            """)
    abstract fun getOrderBySongAsc(): List<LevelWithSongNCreator>

    @Transaction
    @Query("""
        SELECT * FROM level AS l
            ORDER BY (SELECT name FROM song WHERE song.songId=l.song) DESC
            """)
    abstract fun getOrderBySongDesc(): List<LevelWithSongNCreator>

    @Transaction
    @Query("""
        SELECT * FROM level AS l
            ORDER BY (SELECT maxBpm FROM song WHERE song.songId=l.song) ASC
            """)
    abstract fun getOrderByMaxBpmAsc(): List<LevelWithSongNCreator>

    @Transaction
    @Query("""
        SELECT * FROM level AS l
            ORDER BY (SELECT maxBpm FROM song WHERE song.songId=l.song) DESC
            """)
    abstract fun getOrderByMaxBpmDesc(): List<LevelWithSongNCreator>

    @Transaction
    @Query("""
        SELECT * FROM level AS l
            ORDER BY (SELECT minBpm FROM song WHERE song.songId=l.song) ASC
            """)
    abstract fun getOrderByMinBpmAsc(): List<LevelWithSongNCreator>

    @Transaction
    @Query("""
        SELECT * FROM level AS l
            ORDER BY (SELECT minBpm FROM song WHERE song.songId=l.song) DESC
            """)
    abstract fun getOrderByMinBpmDesc(): List<LevelWithSongNCreator>

    @Transaction
    @Query("SELECT * FROM level AS l ORDER BY l.tile ASC")
    abstract fun getOrderByTileAsc(): List<LevelWithSongNCreator>

    @Transaction
    @Query("SELECT * FROM level AS l ORDER BY l.tile DESC")
    abstract fun getOrderByTileDesc(): List<LevelWithSongNCreator>

}