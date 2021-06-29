package io.luxus.adofai.data.source.remote.entity

data class GoogleSheetData(
    val reqId: String,
    val sig: String,
    val status: String,
    val googleSheetTable: GoogleSheetTable,
    val version: String
)