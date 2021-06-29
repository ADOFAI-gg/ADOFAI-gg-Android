package io.luxus.adofai.data.source.remote.entity

import com.google.gson.JsonObject

data class GoogleSheetRow(
    val c: List<JsonObject>
)