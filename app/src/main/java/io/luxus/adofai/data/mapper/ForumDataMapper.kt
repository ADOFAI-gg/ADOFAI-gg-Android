package io.luxus.adofai.data.mapper

import android.util.Log
import com.google.gson.JsonArray
import io.luxus.adofai.data.source.remote.converter.GoogleSheetConverter
import io.luxus.adofai.data.source.remote.service.GoogleSheetService
import io.luxus.adofai.domain.entity.CustomLevel
import io.luxus.adofai.util.Constants
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ForumDataMapper @Inject constructor(
    private val googleSheetService: GoogleSheetService,
    private val googleSheetConverter: GoogleSheetConverter,
) {

    companion object {
        private val TAG = ForumDataMapper::class.java.simpleName
    }

    fun getLevelList(): List<CustomLevel> {
        return getDataList(Constants.GID_ADMIN_MAPS) {
                element -> googleSheetConverter.toCustomLevelData(element)
        }
    }

    private fun <T> getDataList(gid: String, callback: (JsonArray) -> T?): List<T> {
        val result = googleSheetService
            .getData(Constants.KEY_ADMIN, gid)
            .execute().body()!!

        val dataList = ArrayList<T>()
        for (element in result) {
            try {
                val data = callback.invoke(element)
                if (data != null) dataList.add(data)
            } catch (exception: Exception) {
                Log.e(TAG, "failed to convert data: $element", exception)
            }
        }

        return dataList
    }


}