package io.luxus.adofai.data.repository

import android.util.Log
import io.luxus.adofai.data.mapper.ForumDataMapper
import io.luxus.adofai.data.mapper.InitializeDaoMapper
import io.luxus.adofai.data.mapper.InitializeDaoMapper.Companion.KEY_MODE_DATA
import io.luxus.adofai.data.source.local.entity.PlayLog
import io.luxus.adofai.domain.entity.ForumLevel
import io.luxus.adofai.domain.entity.ForumPlayLog
import io.luxus.adofai.domain.repository.InitializeRepository
import kotlinx.coroutines.*
import javax.inject.Inject

class InitializeRepositoryImpl @Inject constructor(
    private val forumDataMapper: ForumDataMapper,
    private val initializeDaoMapper: InitializeDaoMapper,
) : InitializeRepository {

    companion object {
        private val TAG = InitializeRepositoryImpl::class.java.simpleName
    }

    override fun initialize() {
        CoroutineScope(Dispatchers.IO).launch {
            initDatabase()
            Log.i(TAG, "Initialize End")
        }
    }

    private suspend fun initDatabase() {
        when (initializeDaoMapper.getLog(KEY_MODE_DATA)) {
            null->initDataFirst()
        }
        Log.i(TAG, "Database Initialize End")
    }

    private suspend fun initDataFirst() {
        val forumLevelDeferred: Deferred<List<ForumLevel>>
        val forumPlayLogDeferred: Deferred<List<ForumPlayLog>>
        val tagDeferred: Deferred<List<String>>

        coroutineScope {
            forumLevelDeferred = async { return@async forumDataMapper.getLevelList() }
            forumPlayLogDeferred = async { return@async forumDataMapper.getPlayLogList() }
            tagDeferred = async { return@async forumDataMapper.getTagList() }
        }

        initializeDaoMapper.initializeData(
            forumLevelDeferred.await(),
            forumPlayLogDeferred.await(),
            tagDeferred.await()
        )
    }



}