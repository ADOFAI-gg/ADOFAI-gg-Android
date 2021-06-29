package io.luxus.adofai.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.luxus.adofai.domain.entity.CustomLevel
import io.luxus.adofai.domain.usecase.LevelUseCase
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

    private val levelList: MutableList<CustomLevel> = ArrayList()

    private val loadStatus = MutableLiveData(LoadStatus.LOADING)



    enum class LoadStatus {
        FAILED, LOADING, SUCCEED
    }

    fun init() {

    }

    fun load() {
        viewModelScope.launch {
            try {
                val result = withContext(Dispatchers.IO) {
                    levelUseCase.getList()
                }
                levelList.clear()
                levelList.addAll(result)
                if (loadStatus.value != LoadStatus.SUCCEED) loadStatus.value = LoadStatus.SUCCEED
            } catch (t: Throwable) {
                loadStatus.value = LoadStatus.FAILED
                Log.e(TAG, "Failed to fetch data", t)
            }
        }
    }



    fun getLevelList(): List<CustomLevel> = levelList
    fun getLoadStatus(): LiveData<LoadStatus> = loadStatus

}