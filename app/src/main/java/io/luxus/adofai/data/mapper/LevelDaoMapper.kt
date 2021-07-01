package io.luxus.adofai.data.mapper

import io.luxus.adofai.data.source.local.dao.LevelDao
import io.luxus.adofai.domain.entity.*
import javax.inject.Inject

class LevelDaoMapper @Inject constructor(
    private val levelDao: LevelDao
) {

    fun getList(orderOption: OrderOption, desc: Boolean): List<Level> {
        // get
        val levelWithSongNCreatorList = when(orderOption) {
            OrderOption.ID-> if (desc) levelDao.getOrderByIdDesc() else levelDao.getOrderByIdAsc()
            OrderOption.LEVEL-> if (desc) levelDao.getOrderByLevelDesc() else levelDao.getOrderByLevelAsc()
            OrderOption.ARTIST-> if (desc) levelDao.getOrderByArtistDesc() else levelDao.getOrderByArtistAsc()
            OrderOption.CREATOR-> if (desc) levelDao.getOrderByCreatorDesc() else levelDao.getOrderByCreatorAsc()
            OrderOption.MAX_BPM-> if (desc) levelDao.getOrderByMaxBpmDesc() else levelDao.getOrderByMaxBpmAsc()
            OrderOption.MIN_BPM-> if (desc) levelDao.getOrderByMinBpmDesc() else levelDao.getOrderByMinBpmAsc()
            OrderOption.SONG_NAME-> if (desc) levelDao.getOrderBySongDesc() else levelDao.getOrderBySongAsc()
            OrderOption.TILE-> if (desc) levelDao.getOrderByTileDesc() else levelDao.getOrderByTileAsc()
        }

        // convert
        val levelList = ArrayList<Level>(levelWithSongNCreatorList.size)
        for (levelWithSongNCreator in levelWithSongNCreatorList) {

            val artists = ArrayList<Person>(levelWithSongNCreator.songWithArtist.artists.size)
            for (artist in levelWithSongNCreator.songWithArtist.artists) {
                artists.add(Person(artist.name, null))
            }
            val song = Song(
                levelWithSongNCreator.songWithArtist.localSong.name,
                levelWithSongNCreator.songWithArtist.localSong.minBpm,
                levelWithSongNCreator.songWithArtist.localSong.maxBpm,
                artists)

            val creators = ArrayList<Person>(levelWithSongNCreator.creators.size)
            for (creator in levelWithSongNCreator.creators) {
                creators.add(Person(creator.name, null))
            }

            val tags = ArrayList<Tag>(levelWithSongNCreator.tags.size)
            for (tag in levelWithSongNCreator.tags) {
                tags.add(Tag(tag.name))
            }

            levelList.add(Level(
                levelWithSongNCreator.localLevel.levelId, song, creators, tags,
                levelWithSongNCreator.localLevel.level,
                levelWithSongNCreator.localLevel.tile,
                levelWithSongNCreator.localLevel.epilepsyWarning,
                levelWithSongNCreator.localLevel.video,
                levelWithSongNCreator.localLevel.download,
                levelWithSongNCreator.localLevel.workshop ?: ""
            ))
        }

        return levelList
    }


}