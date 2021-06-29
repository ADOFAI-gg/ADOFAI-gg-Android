package io.luxus.adofai.data.source.remote.service

import io.luxus.adofai.data.source.remote.entity.GoogleSheetData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GoogleSheetService {

    @GET("tq")
    fun getData(@Query("key") key: String,
                @Query("gid") gid: String): Call<GoogleSheetData>

}