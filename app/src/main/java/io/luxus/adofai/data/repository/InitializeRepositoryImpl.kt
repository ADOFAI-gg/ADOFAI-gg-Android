package io.luxus.adofai.data.repository

import io.luxus.adofai.domain.repository.InitializeRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class InitializeRepositoryImpl : InitializeRepository {

    override fun initialize() {
        CoroutineScope(Dispatchers.IO).launch {



        }
    }



}