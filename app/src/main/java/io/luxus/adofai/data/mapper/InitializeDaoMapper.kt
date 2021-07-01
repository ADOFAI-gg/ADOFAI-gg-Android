package io.luxus.adofai.data.mapper

import io.luxus.adofai.data.source.local.dao.InitializeDao
import io.luxus.adofai.data.source.local.entity.*
import io.luxus.adofai.data.source.local.entity.relation.LevelCreatorCrossRef
import io.luxus.adofai.data.source.local.entity.relation.LevelTagCrossRef
import io.luxus.adofai.data.source.local.entity.relation.SongArtistCrossRef
import io.luxus.adofai.data.source.remote.entity.ForumLevel
import io.luxus.adofai.data.source.remote.entity.ForumPlayLog
import java.util.*
import javax.inject.Inject

class InitializeDaoMapper @Inject constructor(
    private val initializeDao: InitializeDao
) {

    companion object {
        const val KEY_MODE_DATA = "m_data"

        const val DATA_MODE_LOADING = "loading"
        const val DATA_MODE_COMPLETED = "completed"
    }

    fun getLog(key: String) = initializeDao.getLog(key)

    fun initializeData(forumLevelList: List<ForumLevel>,
                       forumPlayLogList: List<ForumPlayLog>, tagList: List<String>) {

        // get person
        var personId: Long = 1
        val personMap = HashMap<String, Person>()
        for (customLevel in forumLevelList) {
            for (artist in customLevel.artist) {
                if (!personMap.contains(artist)) {
                    personMap[artist] = Person(personId++, artist, null)
                }
            }
            for (creator in customLevel.creator) {
                if (!personMap.contains(creator)) {
                    personMap[creator] = Person(personId++, creator, null)
                }
            }
        }

        for (forumPlayLog in forumPlayLogList) {
            if (!personMap.contains(forumPlayLog.name)) {
                personMap[forumPlayLog.name] = Person(personId++, forumPlayLog.name, null)
            }
        }

        // get tag
        var tagId: Long = 1
        val tagMap = HashMap<String, Tag>()
        for (tag in tagList) {
            tagMap[tag] = Tag(tagId, tag, tagId.toInt())
            tagId++
        }

        // get song
        var songId: Long = 1
        val songMap = HashMap<String, Song>()
        for (customLevel in forumLevelList) {
            if (!songMap.containsKey(customLevel.song)) {
                songMap[customLevel.song] = Song(songId++, customLevel.song,
                    customLevel.minBpm ?: 0.0, customLevel.maxBpm ?: 0.0)
            }
        }

        // get level
        val levelMap = HashMap<Long, Level>()
        for (forumLevel in forumLevelList) {
            if (!levelMap.containsKey(forumLevel.id)) {
                levelMap[forumLevel.id] = Level(forumLevel.id, songMap[forumLevel.song]!!.songId,
                    forumLevel.level, forumLevel.tiles ?: 0, forumLevel.epilepsyWarning,
                forumLevel.video ?: "", forumLevel.download ?: "",
                    forumLevel.workshop)
            }
        }

        // get play log
        val playLogList = ArrayList<PlayLog>()
        for (forumPlayLog in forumPlayLogList) {
            playLogList.add(PlayLog(
                forumPlayLog.id, personMap[forumPlayLog.name]!!.personId,
                forumPlayLog.mapId, forumPlayLog.timeStamp, forumPlayLog.speed,
                forumPlayLog.accuracy, forumPlayLog.pp, forumPlayLog.url
            ))
        }

        // get level creator cross ref
        val levelCreatorCrossRefList = LinkedList<LevelCreatorCrossRef>()
        for (forumLevel in forumLevelList) {
            for (creator in forumLevel.creator) {
                levelCreatorCrossRefList.add(LevelCreatorCrossRef(
                    forumLevel.id, personMap[creator]!!.personId
                ))
            }
        }

        // get song artist cross ref
        val songArtistCrossRefSet = HashSet<String>()
        val songArtistCrossRefList = LinkedList<SongArtistCrossRef>()
        for (forumLevel in forumLevelList) {
            if (!songArtistCrossRefSet.contains(forumLevel.song)) {
                songArtistCrossRefSet.add(forumLevel.song)
                for (artist in forumLevel.artist) {
                    songArtistCrossRefList.add(SongArtistCrossRef(
                            songMap[forumLevel.song]!!.songId, personMap[artist]!!.personId
                    ))
                }
            }
        }

        // get level tag cross ref
        val levelTagCrossRefList = LinkedList<LevelTagCrossRef>()
        for (forumLevel in forumLevelList) {
            for (tagFullText in forumLevel.tags) {
                val tagText = tagFullText.substring(1)
                if (!tagMap.containsKey(tagText)) {
                    tagMap[tagText] = Tag(tagId, tagText, tagId.toInt())
                    tagId++
                }
                levelTagCrossRefList.add(LevelTagCrossRef(
                    forumLevel.id, tagMap[tagText]!!.tagId
                ))
            }
        }

        // submit
        val initializeLog = InitializeLog(KEY_MODE_DATA, DATA_MODE_COMPLETED)
        initializeDao.initDatabase(
            personMap.values, songMap.values, tagMap.values, levelMap.values, playLogList,
            songArtistCrossRefList, levelTagCrossRefList, levelCreatorCrossRefList,
            initializeLog
        )
    }



}