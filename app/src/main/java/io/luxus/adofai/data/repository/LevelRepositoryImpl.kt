package io.luxus.adofai.data.repository

import io.luxus.adofai.data.mapper.ForumDataMapper
import io.luxus.adofai.data.source.remote.entity.ForumLevel
import io.luxus.adofai.data.source.remote.entity.ForumPlayLog
import io.luxus.adofai.domain.repository.LevelRepository
import javax.inject.Inject

class LevelRepositoryImpl @Inject constructor(
    private val forumDataMapper: ForumDataMapper
) : LevelRepository {

    override fun getLevelList(): List<ForumLevel> =
        forumDataMapper.getLevelList()

    override fun getPlayLogList(): List<ForumPlayLog> =
        forumDataMapper.getPlayLogList()

    override fun getTagList(): List<String> =
        forumDataMapper.getTagList()

}