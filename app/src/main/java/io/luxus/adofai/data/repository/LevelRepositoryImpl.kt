package io.luxus.adofai.data.repository

import io.luxus.adofai.data.mapper.ForumDataMapper
import io.luxus.adofai.domain.entity.CustomLevel
import io.luxus.adofai.domain.entity.PlayLog
import io.luxus.adofai.domain.repository.LevelRepository
import javax.inject.Inject

class LevelRepositoryImpl @Inject constructor(
    private val forumDataMapper: ForumDataMapper
) : LevelRepository {

    override fun getLevelList(): List<CustomLevel> =
        forumDataMapper.getLevelList()

    override fun getPlayLogList(): List<PlayLog> =
        forumDataMapper.getPlayLogList()

    override fun getTagList(): List<String> =
        forumDataMapper.getTagList()

}