package io.luxus.adofai.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.luxus.adofai.data.source.remote.entity.ForumLevel
import io.luxus.adofai.domain.entity.Level
import io.luxus.adofai.domain.entity.OrderOption
import io.luxus.adofai.domain.usecase.LevelUseCase
import io.luxus.adofai.presentation.view.custom.type.collection.ListenableList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LevelListViewModel @Inject constructor(
    private val levelUseCase: LevelUseCase
) : ViewModel() {

    companion object {
        private val TAG = LevelListViewModel::class.java.simpleName
    }

    private val levelList = ListenableList<Level>()

    private val loadStatus = MutableLiveData(LoadStatus.LOADING)

    private var firstLoad: Boolean = true

    enum class LoadStatus {
        FAILED, LOADING, SUCCEED
    }

    fun init(listListener: ListenableList.Listener) {
        levelList.setListener(listListener)
    }

    fun firstLoad() {
        if (firstLoad) {
            firstLoad = false
            load()
        }
    }

    private fun load() {
        viewModelScope.launch {
            try {
                val result = withContext(Dispatchers.IO) {
                    levelUseCase.getList(OrderOption.LEVEL, true)
                }

                levelList.changeAllData(result)
                if (loadStatus.value != LoadStatus.SUCCEED) loadStatus.value = LoadStatus.SUCCEED
            } catch (t: Throwable) {
                loadStatus.value = LoadStatus.FAILED
                Log.e(TAG, "Failed to fetch data", t)
            }
        }
    }

    fun getLevelList(): List<Level> = levelList
    fun getLoadStatus(): LiveData<LoadStatus> = loadStatus

}