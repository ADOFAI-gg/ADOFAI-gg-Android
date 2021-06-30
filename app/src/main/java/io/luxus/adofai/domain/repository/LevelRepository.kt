package io.luxus.adofai.domain.repository

import io.luxus.adofai.domain.entity.ForumLevel
import io.luxus.adofai.domain.entity.ForumPlayLog

interface LevelRepository {

    fun getLevelList(): List<ForumLevel>

    fun getPlayLogList(): List<ForumPlayLog>

    fun getTagList(): List<String>

}