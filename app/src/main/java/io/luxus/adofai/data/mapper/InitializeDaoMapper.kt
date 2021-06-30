package io.luxus.adofai.data.mapper

import io.luxus.adofai.data.source.local.dao.InitializeDao
import io.luxus.adofai.data.source.local.entity.Level
import io.luxus.adofai.data.source.local.entity.Person
import io.luxus.adofai.data.source.local.entity.Song
import io.luxus.adofai.data.source.local.entity.Tag
import io.luxus.adofai.domain.entity.ForumLevel
import io.luxus.adofai.domain.entity.ForumPlayLog
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

        for (playLog in forumPlayLogList) {
            if (!personMap.contains(playLog.name)) {
                personMap[playLog.name] = Person(personId++, playLog.name, null)
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
        for (customLevel in forumLevelList) {
            if (!levelMap.containsKey(customLevel.id)) {
                levelMap[customLevel.id] = Level(customLevel.id, songMap[customLevel.song]!!.id,
                    customLevel.level, customLevel.tiles ?: 0, customLevel.epilepsyWarning,
                customLevel.video ?: "", customLevel.download ?: "",
                    customLevel.workshop)
            }
        }

        // get play log
        val playLogList = ArrayList<io.luxus.adofai.data.source.local.entity.PlayLog>

        // get level creator cross ref

        // get tag cross ref

        // get song artist cross ref

        // submit

    }



}