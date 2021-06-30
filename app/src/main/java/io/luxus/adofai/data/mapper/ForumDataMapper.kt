package io.luxus.adofai.data.mapper

import android.util.Log
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

        val result = googleSheetService
            .getData(Constants.KEY_ADMIN, Constants.GID_ADMIN_MAPS)
            .execute().body()!!

        val customLevelList = ArrayList<CustomLevel>()
        for (element in result) {
            try {
                val customLevel = googleSheetConverter.toCustomLevelData(element)
                if (customLevel != null) customLevelList.add(customLevel)
            } catch (exception: Exception) {
                Log.e(TAG, "failed to convert data: $element", exception)
            }
        }

        return customLevelList
    }


}