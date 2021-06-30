package io.luxus.adofai.data.repository

import io.luxus.adofai.data.mapper.ForumDataMapper
import io.luxus.adofai.domain.entity.CustomLevel
import io.luxus.adofai.domain.repository.LevelRepository
import javax.inject.Inject

class LevelRepositoryImpl @Inject constructor(
    private val forumDataMapper: ForumDataMapper
) : LevelRepository {

    override fun getLevelList(): List<CustomLevel> =
        forumDataMapper.getLevelList()

}