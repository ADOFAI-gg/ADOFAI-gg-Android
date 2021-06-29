package io.luxus.adofai.data.source.remote.service.factory

import android.util.Log
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.google.gson.JsonPrimitive
import kotlinx.coroutines.delay
import okhttp3.ResponseBody
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Converter
import retrofit2.Retrofit
import java.io.IOException
import java.lang.reflect.Type


class JsonTextConverterFactory private constructor() : Converter.Factory() {

    companion object {
        fun create() = JsonTextConverterFactory()
    }

    override fun responseBodyConverter(
        type: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit): Converter<ResponseBody, *> {
        return JsonTextResponseBodyConverter()
    }

    internal class JsonTextResponseBodyConverter : Converter<ResponseBody, List<JsonArray>> {

        @Throws(IOException::class)
        override fun convert(responseBody: ResponseBody): List<JsonArray> {

            val result = responseBody.string()

            val from = result.indexOf('(')
            if (from != -1) {
                val to = result.lastIndexOf(')')
                if (from < to) {
                    val cropText = result.substring(from + 1, to)

                    //for (i in 0 until cropText.length / 2000) {
                    //    Log.d("A", cropText.substring(i * 2000, (i + 1) * 2000))
                    //    Thread.sleep(50L)
                    //}
                    //Log.d("A", cropText.substring((cropText.length / 2000) * 2000))

                    val json = JsonParser().parse(cropText).asJsonObject
                    val rows = json["table"].asJsonObject["rows"].asJsonArray

                    val rowsData = ArrayList<JsonArray>()
                    for (element in rows) {
                        rowsData.add(element.asJsonObject["c"].asJsonArray)
                    }

                    //Log.d("TEST", "$cropText")

                    return rowsData
                }
            }

            return listOf()
        }

    }

}