package io.luxus.adofai.data.mapper

import io.luxus.adofai.data.source.local.dao.InitializeDao
import io.luxus.adofai.data.source.local.entity.Level
import io.luxus.adofai.data.source.local.entity.Person
import io.luxus.adofai.data.source.local.entity.Song
import io.luxus.adofai.data.source.local.entity.Tag
import io.luxus.adofai.domain.entity.CustomLevel
import io.luxus.adofai.domain.entity.PlayLog
import java.util.*
import javax.inject.Inject
import kotlin.collections.HashSet

class InitializeDaoMapper @Inject constructor(
    private val initializeDao: InitializeDao
) {

    companion object {
        const val KEY_MODE_DATA = "m_data"

        const val DATA_MODE_LOADING = "loading"
        const val DATA_MODE_COMPLETED = "completed"
    }

    fun getLog(key: String) = initializeDao.getLog(key)

    fun initializeData(customLevelList: List<CustomLevel>,
                     playLogList: List<PlayLog>, tagList: List<String>) {

        // get person
        var personId: Long = 1
        val personMap = HashMap<String, Person>()
        for (customLevel in customLevelList) {
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

        for (playLog in playLogList) {
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
        for (customLevel in customLevelList) {
            if (!songMap.containsKey(customLevel.song)) {
                songMap[customLevel.song] = Song(songId++, customLevel.song, customLevel.minBpm ?: 0.0, customLevel.maxBpm ?: 0.0)
            }
        }

        // get level

        // get play log

        // get level creator cross ref

        // get tag cross ref

        // get song artist cross ref

        // submit

    }



}