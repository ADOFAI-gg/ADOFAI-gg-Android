package io.luxus.adofai.data.source.remote.converter

import com.google.gson.JsonArray
import com.google.gson.JsonElement
import io.luxus.adofai.data.source.remote.entity.ForumLevel
import io.luxus.adofai.data.source.remote.entity.ForumPlayLog
import java.sql.Date
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.collections.ArrayList

@Singleton
class GoogleSheetConverter @Inject constructor() {

    fun toCustomLevelData(data: JsonArray): ForumLevel? {
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
        val levelValue = data[16].asJsonObject["v"].asDouble
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

        val level = when (levelValue) {
            21.0-> -1.0
            22.0-> 0.0
            else-> levelValue
        }

        return ForumLevel(
            id, song, artists, level, creators,
            rawDownload, rawWorkshop, rawVideo,
            epilepsyWarning, minBpm, maxBpm,
            tiles, tags
        )
    }

    fun toPlayLog(data: JsonArray): ForumPlayLog? {
        if (data[0].isJsonNull) return null

        val id = data[0].asJsonObject["v"].asLong
        val timeString = data[1].asJsonObject["v"].asString
        val userName = data[2].asJsonObject["v"].asString
        val userCode = safe(data[3])?.asJsonObject?.get("v")?.asLong ?: 0
        val songID = data[4].asJsonObject["v"].asLong
        val song = data[5].asJsonObject["v"].asString
        val artists = toStringList(safe(data[6])?.asJsonObject?.get("v")?.asString)
        val creators = toStringList(safe(data[7])?.asJsonObject?.get("v")?.asString)
        //val levelStr = data[8].asJsonObject["v"].asString
        val tiles = safe(data[9])?.asJsonObject?.get("v")?.asLong ?: 0
        //val video = data[10].asJsonObject["v"].asString
        val ra = data[11].asJsonObject["v"].asDouble
        val accuracy = data[12].asJsonObject["v"].asDouble * 100
        val speed = data[13].asJsonObject["v"].asDouble * 100
        val playPoint = data[14].asJsonObject["v"].asDouble
        val localRank = safe(data[15])?.asJsonObject?.get("v")?.asLong ?: 0
        val totalRank = data[16].asJsonObject["v"].asLong
        val weighted = safe(data[17])?.asJsonObject?.get("v")?.asDouble ?: 0.0
        //val reserved = data[18].asJsonObject["v"].asString
        val rawURL = safe(data[19])?.asJsonObject?.get("v")?.asString ?: ""

        // Date(2021,2,21,22,47,8)
        val time = Date(SimpleDateFormat("(yyyy,MM,dd,HH,mm,ss)", Locale.US)
            .parse(timeString.substring(4))!!.time)
        //val level = LevelConverter.toDouble(levelStr)
        //val accuracy = accuracyStr.substring(0, accuracyStr.length - 1).toDouble()
        //val speed = speedStr.substring(0, speedStr.length - 1).toLong()

        return ForumPlayLog(
            id, time, userName, userCode,
            songID, song, artists, creators,
            0.0, tiles, ra, accuracy, speed.toLong(), playPoint,
            localRank, totalRank, weighted, rawURL
        )
    }

    fun toTag(data: JsonArray): String? {
        if (data[1].isJsonNull) return null
        return data[1].asJsonObject["v"].asString.substring(1)
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