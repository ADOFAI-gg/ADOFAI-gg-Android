package io.luxus.adofai.data.source.remote.entity

data class GoogleSheetTable(
    val googleSheetCols: List<GoogleSheetCol>,
    val parsedNumHeaders: Int,
    val googleSheetRows: List<GoogleSheetRow>
)