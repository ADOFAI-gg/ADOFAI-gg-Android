package io.luxus.adofai.domain.repository

import io.luxus.adofai.data.source.remote.entity.ForumPlayLog
import io.luxus.adofai.domain.entity.Level
import io.luxus.adofai.domain.entity.OrderOption

interface LevelRepository {

    fun getLevelList(orderOption: OrderOption, desc: Boolean): List<Level>

}