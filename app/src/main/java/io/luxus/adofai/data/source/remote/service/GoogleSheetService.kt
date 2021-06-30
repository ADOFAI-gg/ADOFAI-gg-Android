package io.luxus.adofai.data.source.remote.service

import com.google.gson.JsonArray
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GoogleSheetService {

    @GET("tq")
    fun getData(@Query("key") key: String,
                @Query("gid") gid: String): Call<List<JsonArray>>

    // spreadsheets/u/0/d/1MOz5cmMpYwpBB95DK1Udcti_8eOrswnxWzFurhAz0yg/gviz/tq?gid=739034057
//    @GET("spreadsheets/u/0/d/{key}/gviz/tq")
//    fun getData(@Path("key") key: String,
//                @Query("gid") gid: String): Call<List<JsonArray>>

}