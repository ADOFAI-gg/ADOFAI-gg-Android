package io.luxus.adofai.data.repository

import io.luxus.adofai.data.mapper.ForumDataMapper
import io.luxus.adofai.data.mapper.LevelDaoMapper
import io.luxus.adofai.data.source.remote.entity.ForumLevel
import io.luxus.adofai.data.source.remote.entity.ForumPlayLog
import io.luxus.adofai.domain.entity.Level
import io.luxus.adofai.domain.entity.OrderOption
import io.luxus.adofai.domain.repository.LevelRepository
import javax.inject.Inject

class LevelRepositoryImpl @Inject constructor(
    private val levelDaoMapper: LevelDaoMapper
) : LevelRepository {

    override fun getLevelList(orderOption: OrderOption, desc: Boolean): List<Level> {
        TODO("Not yet implemented")
    }


}