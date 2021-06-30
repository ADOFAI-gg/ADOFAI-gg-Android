package io.luxus.adofai.domain.repository

import io.luxus.adofai.domain.entity.CustomLevel
import io.luxus.adofai.domain.entity.PlayLog

interface LevelRepository {

    fun getLevelList(): List<CustomLevel>

    fun getPlayLogList(): List<PlayLog>

}