package io.luxus.adofai.domain.repository

import io.luxus.adofai.domain.entity.CustomLevel

interface LevelRepository {

    fun getLevelList(): List<CustomLevel>

}