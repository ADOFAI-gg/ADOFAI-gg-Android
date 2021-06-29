package io.luxus.adofai.domain.usecase

import io.luxus.adofai.domain.repository.LevelRepository
import javax.inject.Inject

class LevelUseCase @Inject constructor(
    private val levelRepository: LevelRepository
) {

    fun getList() =
        levelRepository.getLevelList()


}