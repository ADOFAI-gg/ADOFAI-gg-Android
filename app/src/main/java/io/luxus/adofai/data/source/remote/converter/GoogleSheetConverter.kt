package io.luxus.adofai.data.source.remote.converter

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import io.luxus.adofai.domain.model.CustomLevel
import javax.inject.Inject

class GoogleSheetConverter @Inject constructor() {

    fun toCustomLevelData(jsonObject: JsonObject): CustomLevel {
        val data: JsonArray = jsonObject["c"] as JsonArray

        val id = data[0].asJsonObject["v"].asLong
        val song = data[1].asJsonObject["v"].asString
        val artists = toStringList(data[2].asJsonObject["v"].asString)
        //val level = data[3].asJsonObject["v"].asString
        val creators = toStringList(data[4].asJsonObject["v"].asString)
        //val download = data[5].asJsonObject["v"].asString
        //val workshop = data[6].asJsonObject["v"].asString
        //val video = data[7].asJsonObject["v"].asString
        val epilepsyWarningString = data[8].asJsonObject["v"].asString
        val bpmString = data[9].asJsonObject["f"].asString
        val tiles = data[10]?.asJsonObject?.get("v")?.asLong
        val tag1 = data[11]?.asJsonObject?.get("v")?.asString
        val tag2 = data[12]?.asJsonObject?.get("v")?.asString
        val tag3 = data[13]?.asJsonObject?.get("v")?.asString
        val tag4 = data[14]?.asJsonObject?.get("v")?.asString
        val tag5 = data[15]?.asJsonObject?.get("v")?.asString
        val level = data[16].asJsonObject["v"].asDouble
        val rawDownload = data[17]?.asJsonObject?.get("v")?.asString
        val rawWorkshop = data[18]?.asJsonObject?.get("v")?.asString
        val rawVideo = data[19]?.asJsonObject?.get("v")?.asString
        //val reserved = data[20]?.asJsonObject?.get("v")?.asString
        val discord = data[21]?.asJsonObject?.get("v")?.asString


        val epilepsyWarning = epilepsyWarningString != "X"

        val minBpm: Double?
        val maxBpm: Double?
        if (bpmString != null) {
            val idx = bpmString.indexOf('-')
            if (idx == -1) {
                minBpm = bpmString.substring(0, idx).trim().toDouble()
                maxBpm = bpmString.substring(idx + 1).trim().toDouble()
            }
            else {
                minBpm = bpmString.toDouble()
                maxBpm = minBpm
            }
        }
        else {
            minBpm = null
            maxBpm = null
        }

        val tags = ArrayList<String>()
        if (tag1 != null) tags.add(tag1)
        if (tag2 != null) tags.add(tag2)
        if (tag3 != null) tags.add(tag3)
        if (tag4 != null) tags.add(tag4)
        if (tag5 != null) tags.add(tag5)

        return CustomLevel(
            id, song, artists, level, creators,
            rawDownload, rawWorkshop, rawVideo,
            epilepsyWarning, minBpm, maxBpm,
            tiles, tags
        )
    }

    private fun toStringList(text: String): List<String> {
        val list = text.split('&')
        val result = ArrayList<String>(list.size)
        for (elementText in list) {
            result.add(elementText.trim())
        }
        return result
    }



}