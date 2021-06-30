package io.luxus.adofai.data.source.remote.converter

import com.google.gson.JsonArray
import com.google.gson.JsonElement
import io.luxus.adofai.domain.entity.CustomLevel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GoogleSheetConverter @Inject constructor() {

    fun toCustomLevelData(data: JsonArray): CustomLevel? {
        if (data[0].isJsonNull) return null

        val id = data[0].asJsonObject["v"].asLong
        val song = data[1].asJsonObject["v"].asString
        val artists = toStringList(safe(data[2])?.asJsonObject?.get("v")?.asString)
        //val level = data[3].asJsonObject["v"].asString
        val creators = toStringList(data[4].asJsonObject["v"].asString)
        //val download = data[5].asJsonObject["v"].asString
        //val workshop = data[6].asJsonObject["v"].asString
        //val video = data[7].asJsonObject["v"].asString
        val epilepsyWarningString = data[8].asJsonObject["v"].asString
        val bpmString = safe(data[9])?.asJsonObject?.get("f")?.asString
        val tiles = safe(data[10])?.asJsonObject?.get("v")?.asLong
        val tag1 = safe(data[11])?.asJsonObject?.get("v")?.asString
        val tag2 = safe(data[12])?.asJsonObject?.get("v")?.asString
        val tag3 = safe(data[13])?.asJsonObject?.get("v")?.asString
        val tag4 = safe(data[14])?.asJsonObject?.get("v")?.asString
        val tag5 = safe(data[15])?.asJsonObject?.get("v")?.asString
        val level = data[16].asJsonObject["v"].asDouble
        val rawDownload = safe(data[17])?.asJsonObject?.get("v")?.asString
        val rawWorkshop = safe(data[18])?.asJsonObject?.get("v")?.asString
        val rawVideo = safe(data[19])?.asJsonObject?.get("v")?.asString
        //val reserved = data[20]?.asJsonObject?.get("v")?.asString
        //val discord = safe(data[21])?.asJsonObject?.get("v")?.asString


        val epilepsyWarning = epilepsyWarningString != "X"

        val minBpm: Double?
        val maxBpm: Double?
        if (bpmString != null) {
            val idx = bpmString.indexOf('-')
            if (idx != -1) {
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

    private fun safe(element: JsonElement): JsonElement? =
        if (element.isJsonNull) null else element

    private fun toStringList(text: String?): List<String> {
        if (text == null) return listOf("")
        val list = text.split('&')
        val result = ArrayList<String>(list.size)
        for (elementText in list) {
            result.add(elementText.trim())
        }
        return result
    }



}